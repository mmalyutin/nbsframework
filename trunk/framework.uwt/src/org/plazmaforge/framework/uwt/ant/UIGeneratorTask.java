package org.plazmaforge.framework.uwt.ant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.GlobPatternMapper;
import org.apache.tools.ant.util.SourceFileScanner;
import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.generator.BaseGeneratorContext;
import org.plazmaforge.framework.uwt.generator.UIGenerator;
import org.plazmaforge.framework.uwt.storage.XMLReader;


public class UIGeneratorTask extends MatchingTask {

    private Path src;
    
    private File genDir;
    
    private String basepackage;
    
    private boolean usefilepackage = true;
    
    private String packagesuffix;
    
    private String filesuffix;
    
    protected File[] compileList = new File[0];
    
    
    public void setSrcdir(Path srcDir) {
	if (src == null) {
	    src = srcDir;
	} else {
	    src.append(srcDir);
	}
    }

    public Path getSrcdir() {
        return src;
    }

    public void setGendir(File genDir) {
        this.genDir = genDir;
    }


    public File getGendir() {
        return genDir;
    }

    public String getBasepackage() {
        return basepackage;
    }

    public void setBasepackage(String basepackage) {
        this.basepackage = basepackage;
    }

    public boolean isUsefilepackage() {
        return usefilepackage;
    }

    public void setUsefilepackage(boolean usefilepackage) {
        this.usefilepackage = usefilepackage;
    }

    public String getPackagesuffix() {
        return packagesuffix;
    }

    public void setPackagesuffix(String packagesuffix) {
        this.packagesuffix = packagesuffix;
    }

    public String getFilesuffix() {
        return filesuffix;
    }

    public void setFilesuffix(String filesuffix) {
        this.filesuffix = filesuffix;
    }

    public void execute() {
	
	System.out.println("Start UIGenerator");
	
	checkParameters();
	resetFileLists();
	
	
	// scan source directories and dest directory to build up
	String[] list = src.list();
	for (int i = 0; i < list.length; i++) {
	    File srcDir = getProject().resolveFile(list[i]);
	    if (!srcDir.exists()) {
		throw new BuildException("srcdir \"" + srcDir.getPath()
			+ "\" does not exist!", getLocation());
	    }
	    DirectoryScanner ds = this.getDirectoryScanner(srcDir);
	    String[] files = ds.getIncludedFiles();
	    scanDir(srcDir, genDir != null ? genDir : srcDir, files);
	}
	
	boolean listFiles = true;
	if (compileList.length > 0) {
	    log("Generating " + compileList.length + " source file"
		    + (compileList.length == 1 ? "" : "s")
		    + (genDir != null ? " to " + genDir : ""));
	    if (listFiles) {
		for (int i = 0; i < compileList.length; i++) {
		    String filename = compileList[i].getAbsolutePath();
		    log(filename);
		}
	    }
	}
	
	generate();
	
	
	
	
    }
    
    protected void checkParameters() throws BuildException {
	if (src == null || src.size() == 0) {
	    throw new BuildException("srcdir attribute must be set!",  getLocation());
	}
	if (genDir != null && !genDir.isDirectory()) {
	    throw new BuildException("generation directory \"" + genDir
		    + "\" does not exist " + "or is not a directory",
		    getLocation());
	}
    }
    
    
    protected void resetFileLists() {
	compileList = new File[0];
    }
    
    
    protected void scanDir(File srcDir, File destDir, String[] files) {
	GlobPatternMapper m = new GlobPatternMapper();
	m.setFrom("*.ui.xml");
	m.setTo("*.java");
	SourceFileScanner sfs = new SourceFileScanner(this);
	File[] newFiles = sfs.restrictAsFiles(files, srcDir, destDir, m);
	//newFiles = removePackageInfoFiles(newFiles, srcDir, destDir);
	if (newFiles.length > 0) {
	    File[] newCompileList = new File[compileList.length
		    + newFiles.length];
	    System.arraycopy(compileList, 0, newCompileList, 0,
		    compileList.length);
	    System.arraycopy(newFiles, 0, newCompileList, compileList.length,
		    newFiles.length);
	    compileList = newCompileList;
	}
    }    
    
    protected void generate() {
	
	// Get source directory name
	String srcDirName = src.toString(); 
	log("srcdir=" + srcDirName);
	
	// Get generation directory name
	String genDirName = genDir.getAbsolutePath(); 
	log("gendir=" + genDirName);
	
	// Get 'basepackage' directory name
	String basepackageDirName = null;
	if (basepackage != null) {
	    basepackageDirName = toFilePath(basepackage);
	    log("basepackagedir=" + basepackageDirName);
	}
	
	
	XMLReader reader = new XMLReader();
	
	for (int i = 0; i < compileList.length; i++) {
	    String fileName = compileList[i].getName();
	    String filePath = compileList[i].getAbsolutePath();
	    if (!filePath.startsWith(srcDirName)) {
		continue;
	    }
	    
	    // Get patch after source directory 
	    String localPath = filePath.substring(srcDirName.length() + 1);
	    
	    int localPackagePathLen = localPath.length() - fileName.length();
	    String localPackagePath = localPackagePathLen == 0 ? null : localPath.substring(0,  localPackagePathLen - 1);

	    log("localPath=" + localPath);
	    log("localPackagePath=" + localPackagePath);

	    String genPackagePath = null;
	    String genPackageName = null;
	    String genClassName = null;
	    
	    StringBuilder pathBuilder = new StringBuilder();
	    
	    // Add 'basepackage' to path 
	    addFilePath(pathBuilder, basepackageDirName);
	    if (usefilepackage) {
		addFilePath(pathBuilder, localPackagePath);
	    }
	    
	    addFilePath(pathBuilder, toFilePath(packagesuffix));
	    
	    genPackagePath = pathBuilder.toString();
	    if (genPackagePath.isEmpty()) {
		genPackagePath = null;
	    }
	    if (genPackagePath != null) {
		genPackageName = toJavaPackage(genPackagePath);
	    }
	    
	    // Get class name by file name (cut file extension .ui.xml) 
	    int index = fileName.indexOf(".");
	    if (index < 0) {
		genClassName = fileName;
	    } else {
		genClassName = fileName.substring(0, index);
	    }	    
	    log("genClassName=" + genClassName);
	    log("genPackageName=" + genPackageName);
	    
	    
	    String genFileName = null;
	    String genFileFolder = genDirName + (genPackagePath == null ? "" : (File.separator + genPackagePath));
	
	    InputStream is = null;
	    Writer writer = null;
	    
	    try {
		log("file=" + filePath);
		is = new FileInputStream(filePath);
		IData data = reader.readData(is);
		
		// assertNotNull(data);

		// By default generation file name is generation class name
		genFileName = genClassName;
		
		// Get simple class name from data
		String className = (String) data.get(UIObject.PROPERTY_NAME);
		if (className != null) {
		    className = className.trim();
		    if  (className.isEmpty()) {
			className = null;
		    }
		}
		
		// Override generation fileName by special file suffix ($Gen, Gen, Impl, Ext...) 
		if (className != null) {
		    // Add file suffix 
		    if (filesuffix != null) {
			className = className + filesuffix;
			//data.set("name", className);
			data.set(UIBuilder.SYS_PROPERTY_GEN_NAME, className);
		    }
		    genFileName = className;
		}

		genFileName = genDirName + (genPackagePath == null ? "" : (File.separator + genPackagePath)) + File.separator + genFileName + ".java";
		
		UIGenerator generator = new UIGenerator();
		BaseGeneratorContext context = new BaseGeneratorContext();
		context.setPackageName(genPackageName);

		String result = generator.generateClass(context, data);
		
		if (result == null || result.isEmpty()){
		    //TODO
		    continue;
		}
		
		File genFolder = new File(genFileFolder);
		genFolder.mkdirs();
		
		log("genFileName=" + genFileName);
		
	        writer = new BufferedWriter(new FileWriter(genFileName));
	        writer.write(result, 0, result.length());
	        writer.flush();
	    } catch(Throwable e) {
		//log(e.toString(), 1);
		throw new BuildException("Genartion error: " + e.toString());
	    } finally {
		
		try {
		    if (is != null) {
			is.close();
		    }
		    if (writer != null) {
			writer.close();
		    }
		    
		} catch (IOException e) {

		}
	    }
		
	    //log(filename);
	}
    }
    
    /**
     * Add element to path
     * @param sb
     * @param element
     */
    protected void addFilePath(StringBuilder sb, String element) {
	if (element == null) {
	    return;
	}
	if (sb.length() > 0) {
	    sb.append(File.separator);
	}
	sb.append(element);
    }
    
    /**
     * Convert file path to java package
     * @param filePath
     * @return
     */
    protected String toJavaPackage(String filePath) {
	if (filePath == null) {
	    return null;
	}
	return filePath.replace(File.separator, ".");
    }
    
    /**
     * Convert java package to file path
     * @param javaPackage
     * @return
     */
    protected String toFilePath(String javaPackage) {
	if (javaPackage == null) {
	    return null;
	}
	return javaPackage.replace(".", File.separator);
    }

    
}
