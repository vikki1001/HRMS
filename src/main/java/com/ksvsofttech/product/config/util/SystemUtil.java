package com.ksvsofttech.product.config.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemUtil {

private static final Logger LOG = LoggerFactory.getLogger(SystemUtil.class);

private static String OS = System.getProperty("os.name").toLowerCase();
public static String WINDOWS_OS = "windows";
public static String MAC_OS = "mac";
public static String UNIX_LINUX_OS = "unixLinux";
public static String SOLARIS_OS = "solaris";
public static String UNSUPPORTED_OS = "unsupported";
public static final String AFS_CONFIG_FOLDERNAME = "AfsConfig";
public static final String WEB_CONFIG_FOLDERNAME = "Web";
public static final String SERVICE_CONFIG_FOLDERNAME = "Service";
public static final String WEB_CONFIG_FILENAME = "WebConfig.properties";
public static final String SERVICE_CONFIG_FILENAME = "ServiceConfig.properties";
public static final String ISO8583_1993_XML_FILENAME = "iso8583_1993.xml";
public static Properties webCobfigProperties;
public static Properties serviceCobfigProperties;
public static void shutdownSystem() {
try {
Thread.sleep(60000L);
System.exit(1);
} catch (Exception e) {
LOG.error("Error while shutting down system : " + ExceptionUtils.getStackTrace(e));
}
}
public static boolean isWindows() {
  return OS.contains("win");
  }

  public static boolean isMac() {
  return OS.contains("mac");
  }

  public static boolean isUnix() {
  return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
  }

  public static boolean isSolaris() {
  return OS.contains("sunos");
  }
  
  public static String getOperatingSystemType(){
  if (isWindows()) {
  LOG.info("************** Windows OS Detected ***************");
  return WINDOWS_OS;
  } else if (isMac()) {
  LOG.info("************** Mac OS Detected ***************");
  return MAC_OS;
  } else if (isUnix()) {
  LOG.info("************** Unix or Linux OS Detected ***************");
  return UNIX_LINUX_OS;
  } else if (isSolaris()) {
  LOG.info("************** Solaris OS Detected ***************");
  return SOLARIS_OS;
  } else {
  LOG.error("************** Unsupported Operating System Detected ***************");
  return UNSUPPORTED_OS;
  }
  }
  
  
  public static Properties loadWebConfigPropertyFile() {
  if(webCobfigProperties != null) {
  return webCobfigProperties;
  }else {
  webCobfigProperties = loadPropertyFile(WEB_CONFIG_FOLDERNAME, WEB_CONFIG_FILENAME);
  }
  return webCobfigProperties;
  }
  
  public static Properties loadServiceConfigPropertyFile() {
  if(serviceCobfigProperties != null) {
  return serviceCobfigProperties;
  }else {
  serviceCobfigProperties = loadPropertyFile(SERVICE_CONFIG_FOLDERNAME, SERVICE_CONFIG_FILENAME);
  }
  return serviceCobfigProperties;
  }
  
  public static Properties loadPropertyFile(String configFolderName, String configFileName) {
  
  Properties properties=new Properties();
  InputStream input = null;
  try {
  String configFilePath = "";
  Path parentDir = Paths.get("..");
  File currentDirectory = new File(new File(".").getAbsolutePath());
  FileSystem fileSystem = parentDir.getFileSystem();
  fileSystem.getRootDirectories();
  for (Path rootDirectories : fileSystem.getRootDirectories()) {
  if(currentDirectory.getAbsolutePath().contains(rootDirectories.toAbsolutePath().toString())) {
  LOG.info("Searching for {} file at folder path {} ", configFileName, rootDirectories.toAbsolutePath().toString().concat(AFS_CONFIG_FOLDERNAME).concat(File.separator).concat(configFolderName));
  configFilePath = configFilePath.concat(rootDirectories.toAbsolutePath().toString()
  .concat(AFS_CONFIG_FOLDERNAME).concat(File.separator).concat(configFolderName).concat(File.separator).concat(configFileName));
  File configFile = new File(configFilePath);
  if(configFile.exists()) {
  LOG.info("Configuration file found at folder path = {}", configFile);
  input = new FileInputStream(configFile);
  properties.load(input);
  break; 
  }else {
  LOG.error("************ CONFIGURATION FILE {} NOT FOUND AT PATH = {} . SYSTEM STARTUP STOPPED ************", configFileName, rootDirectories.toAbsolutePath().toString().concat(AFS_CONFIG_FOLDERNAME).concat(File.separator).concat(configFolderName));
  SystemUtil.shutdownSystem();
  }
  }
}
}catch (Exception e) {
LOG.error("ERROR OCCURED WHILE LOADING PROPERTY FILE " + ExceptionUtils.getStackTrace(e));
LOG.error(" ************ ERROR WHILE LOADING CONFIGURATION FILE. SYSTEM STARTUP STOPPED ************ ");
SystemUtil.shutdownSystem();
}finally {
if(input != null) {
try {
input.close();
} catch (Exception e) {
LOG.error("Error while closing Input Stream: " + ExceptionUtils.getStackTrace(e));
}
}
}
return properties;
  }

}