/* Drew Wilson */
package com.google.cloud.genomics.localrepo.dto;

import com.google.cloud.genomics.localrepo.DataTransferObject;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
import java.util.Map;

public class Callset extends DataTransferObject {
	
  public static class FileData extends DataTransferObject {
	  
	  public static class MetaInformation extends DataTransferObject {
		  
		private static final ReflectiveHashCodeAndEquals<MetaInformation> HASH_CODE_AND_EQUALS =
			ReflectiveHashCodeAndEquals.create(MetaInformation.class);
		
		public MetaInformation(
			String fileFormat,
			String fileDate,
			String source,
			String reference,
			String contig,
			List<String> info,
			List<String> filter,
			List<String> format) {
		  this.fileFormat = fileFormat;
		  this.fileDate = fileDate;
		  this.source = source;
		  this.reference = reference;
		  this.contig = contig;
		  this.info = info;
		  this.filter = filter;
		  this.format = format;
		}
		
		@JsonCreator public static MetaInformation create(
			@JsonProperty("fileFormat") String fileFormat,
			@JsonProperty("fileDate") String fileDate,
			@JsonProperty("source") String source,
			@JsonProperty("reference") String reference,
			@JsonProperty("contig")	String contig,
			@JsonProperty("info") List<String> info,
			@JsonProperty("filter") List<String> filter,
			@JsonProperty("format") List<String> format) {
		  return new MetaInformation(
			  fileFormat,
			  fileDate,
			  source,
			  reference,
			  contig,
			  info,
			  filter,
			  format);
		}
		
		private final String fileFormat;
		private final String fileDate;
		private final String source;
		private final String reference;
		private final String contig;
		private final List<String> info;
		private final List<String> filter;
		private final List<String> format;

		@Override public boolean equals(Object obj) {
			return HASH_CODE_AND_EQUALS.equals(this, obj);
		}

		public String getFileFormat() {
			return fileFormat;
		}

		public String getFileDate() {
			return fileDate;
		}

		public String getSource() {
			return source;
		}

		public String getReference() {
			return reference;
		}

		public String getContig() {
			return contig;
		}

		public List<String> getInfo() {
			return info;
		}

		public List<String> getFilter() {
			return filter;
		}

		public List<String> getFormat() {
			return format;
		}

		@Override public int hashCode() {
			return HASH_CODE_AND_EQUALS.hashCode(this);
		}
	  }
	  
	  public static class Header extends DataTransferObject {
			
		private static final ReflectiveHashCodeAndEquals<Header> HASH_CODE_AND_EQUALS =
			ReflectiveHashCodeAndEquals.create(Header.class);
		
		public Header(
			String chrom,
			String pos,
			String id,
			String ref,
			String alt,
			String qual,
			String filter,
			String info) {
		  this.chrom = chrom;
		  this.pos = pos;
		  this.id = id;
		  this.ref = ref;
		  this.alt = alt;
		  this.qual = qual;
		  this.filter = filter;
		  this.info = info;
		}
		
		@JsonCreator public static Header create(
			@JsonProperty("chrom") String chrom,
			@JsonProperty("pos") String pos,
			@JsonProperty("id") String id,
			@JsonProperty("ref") String ref,
			@JsonProperty("alt") String alt,
			@JsonProperty("qual") String qual,
			@JsonProperty("filter") String filter,
			@JsonProperty("info") String info) {
		  return new Header(
			chrom,
			pos,
			id,
			ref,
			alt,
			qual,
			filter,
			info);
		}
		
		private final String chrom;
		private final String pos;
		private final String id;
		private final String ref;
		private final String alt;
		private final String qual;
		private final String filter;
		private final String info;
		  
		@Override public boolean equals(Object obj) {
			return HASH_CODE_AND_EQUALS.equals(this, obj);
		}

		public String getChrom() {
			return chrom;
		}

		public String getPos() {
			return pos;
		}

		public String getId() {
			return id;
		}

		public String getRef() {
			return ref;
		}

		public String getAlt() {
			return alt;
		}

		public String getQual() {
			return qual;
		}

		public String getFilter() {
			return filter;
		}

		public String getInfo() {
			return info;
		}

		@Override public int hashCode() {
			return HASH_CODE_AND_EQUALS.hashCode(this);
		}
	  }
	  
	  public static class Record extends DataTransferObject {
		  
		private static final ReflectiveHashCodeAndEquals<Record> HASH_CODE_AND_EQUALS =
			ReflectiveHashCodeAndEquals.create(Record.class);
		
		public Record(
			String chromosome,
			int position,
			String id,
			String referenceBases,
			String alternateAlleles,
			float quality,
			String filter,
			String info) {
		  this.chromosome = chromosome;
		  this.position = position;
		  this.id = id;
		  this.referenceBases = referenceBases;
		  this.alternateAlleles = alternateAlleles;
		  this.quality = quality;
		  this.filter = filter;
		  this.info = info;
		}
		
		@JsonCreator public static Record create(
			@JsonProperty("chromosome") String chromosome,
			@JsonProperty("position") int position,
			@JsonProperty("id") String id,
			@JsonProperty("referenceBases") String referenceBases,
			@JsonProperty("alternateAlleles") String alternateAlleles,
			@JsonProperty("quality") float quality,
			@JsonProperty("filter") String filter,
			@JsonProperty("info") String info) {
		  return new Record(
			  chromosome,
			  position,
			  id,
			  referenceBases,
			  alternateAlleles,
			  quality,
			  filter,
			  info);
		}
		
		private final String chromosome;
		private final int position;
		private final String id;
		private final String referenceBases;
		private final String alternateAlleles;
		private final float quality;
		private final String filter;
		private final String info;

		@Override public boolean equals(Object obj) {
			return HASH_CODE_AND_EQUALS.equals(this, obj);
		}

		public String getChromosome() {
			return chromosome;
		}

		public int getPosition() {
			return position;
		}

		public String getId() {
			return id;
		}

		public String getReferenceBases() {
			return referenceBases;
		}

		public String getAlternateAlleles() {
			return alternateAlleles;
		}

		public float getQuality() {
			return quality;
		}

		public String getFilter() {
			return filter;
		}

		public String getInfo() {
			return info;
		}

		@Override public int hashCode() {
			return HASH_CODE_AND_EQUALS.hashCode(this);
		}

		public static Record createDefaultGroup() {
			return Record.create(null, 0, null, null, null, 0, null, null);
		}
	  }

	  private static final ReflectiveHashCodeAndEquals<FileData> HASH_CODE_AND_EQUALS =
		ReflectiveHashCodeAndEquals.create(FileData.class);
	  
	  public FileData(
		  String fileUri,
		  List<MetaInformation> metaInformation,
		  List<Header> headers,
		  List<Record> records) {
		this.fileUri = fileUri;
		this.metaInformation = metaInformation;
		this.headers = headers;
		this.records = records;
	  }
	  
	  private final String fileUri;
	  private final List<MetaInformation> metaInformation;
	  private final List<Header> headers;
	  private final List<Record> records;

	  @JsonCreator public static FileData create(
		  @JsonProperty("fileUri") String fileUri,
		  @JsonProperty("metaInformation") List<MetaInformation> metaInformation,
		  @JsonProperty("headers") List<Header> headers,
		  @JsonProperty("records") List<Record> records) {
		return new FileData(fileUri, metaInformation, headers, records);  
	  }
	  
	  @Override public boolean equals(Object obj) {
		  return HASH_CODE_AND_EQUALS.equals(this, obj);
	  }

	  public String getFileUri() {
		return fileUri;
	  }

	  public List<MetaInformation> getMetaInformation() {
		return metaInformation;
	  }

	  public List<Header> getHeaders() {
		return headers;
	  }

	  public List<Record> getRecords() {
		return records;
	  }

	  @Override public int hashCode() {
		  return HASH_CODE_AND_EQUALS.hashCode(this);
	  }
  }

  private static final ReflectiveHashCodeAndEquals<Callset> HASH_CODE_AND_EQUALS =
      ReflectiveHashCodeAndEquals.create(Callset.class);

  @JsonCreator public static Callset create(
      @JsonProperty("id") String id,
      @JsonProperty("name") String name,
      @JsonProperty("datasetId") String datasetId,
      @JsonProperty("created") long created,
      @JsonProperty("info") Map<String, String> info,
      @JsonProperty("fileData") List<FileData> fileData) {
    return new Callset(id, name, datasetId, created, info, fileData);
  }

  private final String id;
  private final String name;
  private final String datasetId;
  private final long created;
  private final Map<String, String> info;
  private final List<FileData> fileData;

  private Callset(
	  String id,
	  String name,
	  String datasetId,
	  long created,
	  Map<String, String> info,
	  List<FileData> fileData) {
    this.id = id;
    this.name = name;
    this.datasetId = datasetId;
    this.created = created;
    this.info = info;
    this.fileData = fileData;
  }

  @Override public boolean equals(Object obj) {
    return HASH_CODE_AND_EQUALS.equals(this, obj);
  }

  public String getId() {
    return id;
  }
  
  public String getName() {
	  return name;
  }
  
  public String getDatasetId() {
	  return datasetId;
  }
  
  public long getCreated() {
	  return created;
  }
  
  public Map<String, String> getInfo() {
	  return info;
  }

  public List<FileData> getFileData() {
	return fileData;
  }

  @Override public int hashCode() {
	  return HASH_CODE_AND_EQUALS.hashCode(this);
  }
}