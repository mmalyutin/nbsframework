; install-swing-demo.nsi
;

;--------------------------------

; The name of the installer
Name "${app.name}"

; The file to write
OutFile "${installer.name}"

; The default installation directory
InstallDir $DESKTOP\${install.directory}

; Request application privileges for Windows Vista
RequestExecutionLevel user

;--------------------------------

; Pages

Page directory
Page instfiles

;--------------------------------

; The stuff to install
Section "" ;No components page, name is not important

  ; Set output path to the installation directory.
  SetOutPath $INSTDIR
  
  FILE "${project.directory}\start.bat"
  FILE "${project.directory}\start.sh"
  
  SetOutPath "$INSTDIR\lib"
  
  FILE "${project.directory}\lib\*.*"
  
SectionEnd ; end the section
