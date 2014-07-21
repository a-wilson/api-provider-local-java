package com.google.cloud.genomics.localrepo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.function.Supplier;

import org.joda.time.DateTimeUtils;

import com.google.cloud.genomics.localrepo.dto.Callset;
import com.google.cloud.genomics.localrepo.util.Suppliers;
import com.google.cloud.genomics.localrepo.vcf.MetaInformation;

public class VcfFilesCallset {
	
	public static VcfFilesCallset create(
		String callsetId,
		String sample,
		String datasetId,
		Set<VcfFile> vcfFiles) {
	  return new VcfFilesCallset(callsetId, sample, datasetId, vcfFiles);
	}
	
	private final Set<VcfFile> vcfFiles;
	private final String datasetId;
	private final String callsetId;
	private final String name;
	private final Map<String, String> info;
	private final Supplier<Callset> callset;
	private final Supplier<MetaInformation> metaInformation;
	
	private VcfFilesCallset(String callsetId, String name, String datasetId,
		Set<VcfFile> vcfFiles) {
	  this.callsetId = callsetId;
	  this.name = name;
	  this.datasetId = datasetId;
	  this.info = null;
	  this.vcfFiles = vcfFiles;
	  this.callset = 
		  Suppliers.memoize(() -> Callset.create(
			  getCallsetId(),
			  getName(),
			  getDatasetId(),
			  DateTimeUtils.currentTimeMillis(),
			  getInfo(),
			  getVcfFiles()
		  		  .stream()
		  		  .map(VcfFile::getFileData)
		  		  .collect(
		  			  Collectors.toCollection(() -> new ArrayList<>(new TreeSet<>(Comparator
		  				  .comparing(Callset.FileData::getFileUri)))))));
	  this.metaInformation = null;
	}
	
	public Set<VcfFile> getVcfFiles() {
		return vcfFiles;
	}
	
	public String getDatasetId() {
		return datasetId;
	}
	
	public Callset getCallset() {
		return callset.get();
	}
	
	public String getCallsetId() {
		return callsetId;
	}
	
	public String getName() {
		return name;
	}
	
	public Map<String, String> getInfo() {
		return info;
	}

	@Override public int hashCode() {
		return Objects.hash(
			getCallsetId(),
			getDatasetId(),
			getVcfFiles());
	}
	
	@Override public String toString() {
		return String.format(
			"callsetId: %s datasetId: %s vcfFiles: %s",
	        getCallset(),
	        getDatasetId(),
	        getVcfFiles());
	}
	
	MetaInformation getHeader() {
		return metaInformation.get();
	}
}
