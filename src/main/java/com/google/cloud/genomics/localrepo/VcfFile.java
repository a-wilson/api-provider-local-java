package com.google.cloud.genomics.localrepo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.cloud.genomics.localrepo.dto.Callset;
import com.google.cloud.genomics.localrepo.dto.Callset.FileData.Header;
import com.google.cloud.genomics.localrepo.dto.Callset.FileData.Record;
import com.google.cloud.genomics.localrepo.dto.Callset.FileData;
import com.google.cloud.genomics.localrepo.util.Suppliers;
import com.google.cloud.genomics.localrepo.vcf.MetaInformation;
import com.google.cloud.genomics.localrepo.vcf.VCFHeader;
import com.google.cloud.genomics.localrepo.vcf.VCFReader;
import com.google.cloud.genomics.localrepo.vcf.VCFRecord;
import com.google.common.base.Optional;


public class VcfFile {

	public static Optional<VcfFile> create(File file) {
		return isReadableFile(file) && file.getName().endsWith(".vcf")
				? Optional.of(new VcfFile(file))
				: Optional.<VcfFile>absent();
	}
	
	private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();
	
	private static final Logger LOGGER = Logger.getLogger(VcfFile.class.getName());
	  
	private static boolean isReadableFile(File file) {
		return file.isFile() && file.canRead();
	}
	  
	final File file;
	
	private final Supplier<MetaInformation> metaInformation = Suppliers.memoize(
		new Supplier<MetaInformation>() {
			@Override public MetaInformation get() {
				try (VCFReader reader = open()) {
					return reader.parseMetaInfo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	
	private final Supplier<VCFHeader> header = Suppliers.memoize(
		new Supplier<VCFHeader>() {
			@Override public VCFHeader get() {
				try (VCFReader reader = open()) {
					return reader.parseHeader(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	
	private final Supplier<VCFRecord> record = Suppliers.memoize(
		new Supplier<VCFRecord>() {
			@Override public VCFRecord get() {
				try (VCFReader reader = open()) {
					BufferedReader br = new BufferedReader(new FileReader(file));
					return reader.parseRecord(br.readLine());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	
	private final Supplier<Callset.FileData> fileData = Suppliers.memoize(Suppliers.compose(
		new Function<VCFReader, Callset.FileData>() {
		    @Override public Callset.FileData apply(VCFReader reader) {
		      List<Record> variantRecords = reader.getRecords().stream()
		          .map(record -> {
		            return Callset.FileData.Record.create(
		            	record.getChromosome(),
		            	record.getPosition(),
		            	record.getId(),
		            	record.getReferenceBases(),
		            	record.getAlternateAlleles(),
		            	record.getQuality(),
		            	record.getFilter(),
		            	record.getInfo());
		          })
		          .collect(Collectors.toList());
		      if (variantRecords.isEmpty()) {
		        variantRecords.add(Callset.FileData.Record.createDefaultGroup());
		      }
		
		      return Callset.FileData.create(
		        String.format("file://%s", file.getAbsolutePath()),
		        Arrays.asList(Callset.FileData.MetaInformation.create(
		        	reader.getFileFormat(),
		        	reader.getFileDate(),
		        	reader.getSource(),
		        	reader.getReference(),
		        	reader.getContig(),
		        	reader.getInfo(),
		        	reader.getFilter(),
		        	reader.getFormat())),
		        Arrays.asList(Callset.FileData.Header.create(
	        		reader.getChrom(),
	        		reader.getPos(),
	        		reader.getId(),
	        		reader.getRef(),
	        		reader.getAlt(),
	        		reader.getQual(),
	        		reader.getHeaderFilter(),
	        		reader.getHeaderInfo())),
		        variantRecords);
		    }
		},
		metaInformation));
 
	private VcfFile(final File vcfFile) {
		file = vcfFile;
	}
	
	VCFReader createReader() {
		try {
			return new VCFReader(new BufferedReader(new FileReader(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public final File getFile() {
		return file;
	}
	
	public final Callset.FileData getFileData() {
		return null;
	}
	
	@Override public final int hashCode() {
		return getFile().hashCode();
	}
	
	final VCFReader open() {
		VCFReader reader = createReader();
		//reader.setValidationStringency(ValidationStringency.SILENT);
		return reader;
	}
	
	@Override public final String toString() {
		return getFile().toString();
	}
}
