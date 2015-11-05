/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package org.plazmaforge.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;


/** 
 * @author ohapon
 */
public class FileUtils {

    
    /**
     * Return parent folder of the file
     * @param fileName
     * @return <code>File</code>
     */
    public static File getFolder(String fileName) {
	if (fileName == null) {
	    return null;
	}
	File file = new File(fileName);
	return file.getParentFile();
    }
    

    /**
     * Return name of parent folder of the file
     * @param fileName
     * @return
     */
    public static String getFolderName(String fileName) {
	if (fileName == null) {
	    return null;
	}
	File file = new File(fileName);
	return file.getParent();
    }

    
    /**
     * Return index of last folder separator of absolute file.
     * For example: File Name - C:/MyDir/file.txt, Index - 8 
     * @param fileName
     * @return
     */
    public static int getLastIndexOfFolderSeparator(String fileName) {
	String folderName = getFolderName(fileName);
	if (StringUtils.isEmpty(folderName)) {
	    return -1;
	}
	return folderName.length();
    }
    
    /**
     * Return index of file extension.
     * It is position of '.'
     * @param fileName
     * @return
     */
    public static int getIndexOfFileExt(String fileName) {
	if (fileName == null) {
	    return -1;
	}
	int index = fileName.lastIndexOf(".");
	
	// Check situation like: 'C:\\folder\\x.y.z\\myfile'
	// Last index of '.' is not position of file extension
	if (index > 0 && index > getLastIndexOfFolderSeparator(fileName)) {
	    return index;
	}
	return -1;
    }

    /**
     * Return simple file name
     * 
     * For example: 
     *  File Name - 'C:\\folder\\myfile.txt'
     *  Result - 'myfile.txt'
     *  
     * @param fileName
     * @return
     */
    public static String getFileName(String fileName) {
	if (fileName == null) {
	    return null;
	}
	int index = getLastIndexOfFolderSeparator(fileName);
	if (index < 0) {
	    return fileName;
	}
	index++;
	if (index > fileName.length() - 1) {
	    return "";
	}
	return fileName.substring(index);
	
    }
    
    /**
     * Parse file name and return array:
     * - parent folder
     * - simple file name
     * 
     * @param fileName
     * @return
     */
    public static String[] parseFileName(String fileName) {
	String[] result = new String[2];
	if (fileName == null) {
	    return result;
	}
	fileName = fileName.trim();
	if (fileName.length() == 0) {
	    return result;
	}
	String folderName = getFolderName(fileName);
	if (StringUtils.isEmpty(folderName)) {
	    result[1] = fileName;
	    return result;
	}
	result[0] = folderName;
	int index = folderName.length();
	index++;
	if (index > fileName.length() - 1) {
	    return result;
	}
	result[1] = fileName.substring(index);
	return result;
    }

    /**
     * Return file extension
     * 
     * For example:
     *  File Name - 'C:\\folder\\myfile.txt'
     *  Result - 'txt'
     *  
     * @param fileName
     * @return
     */
    public static String getFileExt(String fileName) {
	if (fileName == null) {
	    return null;
	}
	int index = getIndexOfFileExt(fileName);
	if (index < 0) {
	    return "";
	}
	index++;
	if (index > fileName.length() - 1) {
	    return "";
	}
	return fileName.substring(index);
    }

    /**
     * Return simple file name without extension.
     * 
     * For example:
     *  File Name - 'C:\\folder\\myfile.txt'
     *  Result - 'myfile'
     * 
     * @param fileName
     * @return
     */
    public static String getFileNameWithoutExt(String fileName) {
	if (fileName == null) {
	    return null;
	}
	fileName = getFileName(fileName); // get simple file name (non absolute file name)
	int index = getIndexOfFileExt(fileName);
	if (index < 0) {
	    return fileName;
	}
	return fileName.substring(0, index);
    }

    
    /**
     * Return true if the file path is absolute
     * @param fileName
     * @return
     */
    public static boolean isAbsoluteFile(String fileName) {
	if (fileName == null) {
	    return false;
	}
	File file = new File(fileName);
	return file.isAbsolute();
    }
    
    /**
     * Return true if file is exists
     * @param fileName
     * @return
     */
    public static boolean exists(String fileName) {
	if (fileName == null) {
	    return false;
	}
	File file = new File(fileName);
	return file.exists();
    }
    
    /**
     * Build file path by elements
     * @param elements
     * @return
     */
    public static String getPath(String... elements) {
	if (elements == null || elements.length == 0) {
	    return null;
	}
	if (elements.length == 1) {
	    return elements[0]; 
	}
	if (elements.length == 2) {
	    return elements[0] + File.separator + elements[1]; 
	}
	StringBuilder builder = new StringBuilder();
	for (int i = 0; i < elements.length; i++) {
	    if  (i > 0) {
		builder.append(File.separator);
	    }
	    builder.append(elements[i]);
	}
	return builder.toString();
    }
    
    /**
     * Add file extension to file name
     * @param fileName
     * @param ext
     * @return
     */
    public static String completeFileExt(String fileName, String ext) {
	return completeFileExt(fileName, ext, true);
    }
    
    /**
     * Add file extension to file name
     * If <forceReplace> is true then replace old file extension
     * @param fileName
     * @param ext
     * @param forceReplace
     * @return
     */
    public static String completeFileExt(String fileName, String ext, boolean forceReplace) {
	if (fileName == null) {
	    return null;
	}
	if (ext == null) {
	    return fileName;
	}
	
	// If file name is empty then return
	fileName = fileName.trim();
	if (fileName.length() == 0) {
	    return fileName;
	}
	
	// If file extension is empty then return
	ext = ext.trim();
	if (ext.length() == 0) {
	    return fileName;
	}
	
	// Fix file extension
	if (!ext.startsWith(".")) {
	    ext = "." + ext;
	}
	
	// If file name has the extension then return 
	if (fileName.endsWith(ext)) {
	    return fileName;
	}
	
	// Find and replace file extension 
	int index = getIndexOfFileExt(fileName);
	if (index > 0) {
	    if (forceReplace) {
		fileName = fileName.substring(0, index);
		return fileName + ext;
	    }
	    return fileName;
	}
	return fileName + ext;
    }

    
    /**
     * Copy data from <fileFrom> to <fileTo> 
     * @param fileFrom
     * @param fileTo
     * @throws IOException
     */
    public static void copyFile(File fileFrom, File fileTo) throws IOException {
	if (fileFrom == null || !fileFrom.exists()) {
	    return;
	}
	BufferedInputStream is = new BufferedInputStream(new FileInputStream(fileFrom));
	BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(fileTo));
	transferStreams(is, os);
    }
    
    /**
     * Transfer data form input stream to output stream
     * @param is
     * @param os
     * @throws IOException
     */
    public static void transferStreams(InputStream is, OutputStream os) throws IOException {
	if (is == null || os == null) {
	    return;
	}

	byte data[] = new byte[8192];

	while (true) {
	    int i = is.read(data);
	    if (i == -1) {
		break;
	    }
	    os.write(data, 0, i);
	}

	try {
	    is.close();
	} catch (IOException ex) {
	}
	try {
	    os.close();
	} catch (IOException ex) {
	}
	return;
    }
    
    
    public static String getCanonicalPath(String folder, String path) throws IOException {
	boolean isEmptyFolder = StringUtils.isEmpty(folder);
	boolean isEmptyPath = StringUtils.isEmpty(path);
	
	if (isEmptyFolder && isEmptyPath)  {
	    return null;
	}
	
	if (isEmptyFolder) {
	    return path; 
	}
	
	if (isEmptyPath) {
	    return folder; 
	}
	
	if (!isRelativePath(path)) {
	    return path;
	}
	folder = folder.trim();
	path = path.trim();
	String canonicalPath = folder + (isStartWithFileSeparator(path) ? "" : File.separator) + path;
	File file = new File(canonicalPath);
	return file.getCanonicalPath();
	
    }
    
    public static boolean isRelativePath(String path) {
	if (path == null) {
	    return false;
	}
	path = path.trim();
	if (path.startsWith(".") || path.startsWith(File.separator) || path.startsWith("/")) {
	    return true;
	}
	return false;
    }
    
    public static boolean isStartWithFileSeparator(String path) {
	if (path == null) {
	    return false;
	}
	path = path.trim();
	if (path.startsWith(File.separator) || path.startsWith("/")) {
	    return true;
	}
	return false;
    }

    /**
     * Return the string representation of the file. 
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readFileAsString(String fileName) throws IOException {
	StringBuffer fileData = new StringBuffer(1000);
	BufferedReader reader = null;
	try {
	    reader = new BufferedReader(new FileReader(fileName));
	    char[] buf = new char[1024];
	    int numRead = 0;
	    while ((numRead = reader.read(buf)) != -1) {
		String readData = String.valueOf(buf, 0, numRead);
		fileData.append(readData);
		buf = new char[1024];
	    }
	    return fileData.toString();

	} finally {
	    if (reader != null) {
		try {
		    reader.close();
		} catch (IOException ex) {
		    
		}
	    }
	}
    }
    
    /**
     * Write the string to file.
     * @param fileName
     * @param text
     * @throws IOException
     */
    public static void writeFileAsString(String fileName, String text) throws IOException {
	FileWriter writer = null;
	try {
	    writer = new FileWriter(fileName);
	    writer.write(text);
	    writer.flush();
	} finally {
	    if (writer != null)
		try {
		    writer.close();
		} catch (IOException ex) {

		}
	}
    }
    
    public static URL getURL(ClassLoader classLoader, String path) throws IOException {
	if (path == null) {
	    return null;
	}
	// Normalize path for ClassLoader (remove start '/')
	if (path.startsWith("/")) {
	    path = path.substring(1, path.length());
	}	
	if (classLoader == null) {
	    classLoader = getCurrentClassLoader();
	}
	return classLoader.getResource(path);
    }

    public static URL getURL(Class<?> klass, String path) throws IOException {
	if (path == null) {
	    return null;
	}
	
	if (klass == null) {
	    klass = FileUtils.class;
	}
	String newPath = path;
	
	// Normalize path for Class (add start '/')
	if (!path.startsWith("/")) {
	    newPath = "/" + path;
	}
	URL url = klass.getResource("/" + path);
	if  (url == null) {
	    //Why?
	    
	    // Normalize path for ClassLoader (remove start '/')
	    if (path.startsWith("/")) {
		newPath = path.substring(1, path.length());
	    }
	    url = getCurrentClassLoader(klass).getResource(newPath);
	}
	
	return url;
    }

    private static ClassLoader getCurrentClassLoader() {
	return getCurrentClassLoader(null);
    }
    
    private static ClassLoader getCurrentClassLoader(Class<?> klass)  {
	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	if (classLoader == null) {
	    if (klass == null) {
		klass = FileUtils.class;
	    }
	    classLoader = klass.getClassLoader();
	}
	return classLoader;
    }
    
    
    public static InputStream getResource(String path) throws IOException {
	return getResource(getCurrentClassLoader(), path);
    }
    
    /**
     * Return InputStream by path
     * Support more protocols: 'file:', 'classpath:', 'jar:'
     * 
     * @param classLoader only for 'classpath:', 'jar:' protocols
     * @param path
     * @return
     * @throws IOException
     */
    public static InputStream getResource(ClassLoader classLoader, String path) throws IOException {
	if (path == null) {
	    return null;
	}
	
	String[] protocolPath = parseProtocolPath(path);
	String protocol = protocolPath[0];
	String newPath = protocolPath[1];

	if (protocol == null) {
	    protocol = "file"; // Default protocol
	}

	if (protocol.equals("file")) {
	    return getFileResource(newPath);
	} if (protocol.equals("classpath")) {
	    return getClassResource(getURL(classLoader, newPath));
	} if (protocol.equals("jar")) {
	    return getJarResource(getURL(classLoader, newPath));
	} if ("http".equals(protocol) || "https".equals(protocol) || "ftp".equals(protocol)) {
	    //TODO: Must implement other protocols
	    return null;
	}
	
	return getFileResource(path);
    }
    
    private static String[] parseProtocolPath(String path) {
	if (path == null) {
	    return null;
	}
	int pos = path.indexOf(":");
	String protocol = null;
	String newPath = path;
	if (pos > -1) {
	    // Get path protocol ('classpath:' , 'file:'...)
	    protocol = path.substring(0, pos);
	    if (protocol.isEmpty()) {
		// Reset protocol if empty
		protocol = null;
	    }
	    // Normalize path: remove protocol with ':'
	    newPath = path.substring(pos + 1);
	}
	String[] result = new String[2];
	result[0] = protocol;
	result[1] = newPath;
	return result;
    }
    
    private static InputStream getFileResource(String path) throws IOException {
	if (path == null) {
	    return null;
	}
	return new FileInputStream(path);
    }
    
    private static InputStream getClassResource(URL url) throws IOException {
	if (url == null) {
	    return null;
	}
	String protocol = url.getProtocol();
	String path = null;
	if ("file".equals(protocol)) {
	    try {
		path = toURI(url).getSchemeSpecificPart();
	    } catch (URISyntaxException e) {
		throw new IOException(e);
	    }
	    return getFileResource(path);
	} else if ("jar".equals(protocol)) {
	    path = url.getPath();
	    return getJarResource(path);
	}
	return null;
    }
    
    private static InputStream getClassResource(Class kclass, String path) throws IOException {
	if (path == null) {
	    return null;
	}
	URL url = getURL(kclass, path);
	return getClassResource(url);
    }
    
    private static InputStream getClassResource(ClassLoader classLoader, String path) throws IOException {
	if (path == null) {
	    return null;
	}
	
	URL url = getURL(classLoader, path);
	return getClassResource(url);
    }
    
    private static InputStream getJarResource(URL url) throws IOException {
	if (url == null) {
	    return null;
	}
	return getJarResource(url.getPath());
    }
    
    private static InputStream getJarResource(String path) throws IOException {
	if (path == null) {
	    return null;
	}    
	int index = path.indexOf("!");
	int startIndex = index + 2;
	if (index == -1 || startIndex >= path.length()) {
	    // Bad jar path
	    return null;
	}
	// Separate path: <jar_name>!<entry_name>
	String entryPath = path.substring(startIndex);
	String jarPath = path.substring(0, index);
	jarPath = jarPath.replace("%20", " "); // fixed path: bad code

	URL jarUrl = new URL(jarPath);
	return getJarResource(jarUrl.getFile(), entryPath);
    }

    private static InputStream getJarResource(String jarFileName, String path) throws IOException {
	if (jarFileName == null || path == null) {
	    return null;
	}

	// Normalize path
	path = StringUtils.nullIfEmpty(path);
	if (path == null) {
	    return null;
	}

	JarFile jarFile = null;
	try {
	    jarFile = createJarFile(jarFileName);
	    Enumeration<JarEntry> jarEntries = jarFile.entries();

	    while (jarEntries.hasMoreElements()) {
		ZipEntry ze = jarEntries.nextElement();
		String name = ze.getName();

		// We use only file
		if (ze.isDirectory()) {
		    continue;
		}

		if (name.equals(path)) {
		    return jarFile.getInputStream(ze);
		}
	    }
	    throw new FileNotFoundException(jarFileName + "!" + path);
	} finally {
	    closeJarFile(jarFile);
	}
    }

    private static URI toURI(URL url) throws URISyntaxException {
	return toURI(url.toString());
    }

    private static URI toURI(String location) throws URISyntaxException {
	return new URI(StringUtils.replaceAll(location, " ", "%20"));
    }

    private static JarFile createJarFile(String fileName) throws IOException {
	return new JarFile(fileName);
    }

    private static void closeJarFile(JarFile jarFile) {
	try {
	    if (jarFile != null) {
		jarFile.close();
	    }
	} catch (IOException ioe) {
	    // do nothing
	}
    }
}
