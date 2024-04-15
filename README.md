## Description
The selenium tests are used for testing the Parasoft Findings Jenkins plugin. All test cases are running on **Chrome** and **Windows** currently.

## Prerequisites for tested projects
* For [CppProjectTemplate](https://code.parasoftcn.com/projects/PF/repos/cppprojecttemplate/browse) project:
  * Cmake
  * MinGW and Set "<MinGW_INSTALL_DIR>\bin" to environment variable
  * Python
  * CPPTest std
* For [JavaProjectTemplate](https://code.parasoftcn.com/projects/PF/repos/javaprojecttemplate/browse) project:
  * Configure the Jtest Plugin for Maven at first. Reference document: [Configuring the Jtest Plugin for Maven](https://docs.parasoft.com/display/JTEST20231/Configuring+the+Jtest+Plugin+for+Maven#ConfiguringtheJtestPluginforMaven-InitialSetupInitialSetup)
  * Jtest
* For [BankExample.NET](https://code.parasoftcn.com/projects/PF/repos/bankexample.net/browse) project:
  * dotNet SDK
  * If using dotTEST installer, the SDK will be installed in dotTEST directory.
  * dotTEST
* For [FlowAnalysisCpp](https://code.parasoftcn.com/projects/PF/repos/flowanalysiscpp/browse) project:
  * MinGW
  * CPPTest pro

## Running tests
Run the following command in root directory:

```
mvn test -Dtest=%TARGET_TEST_CASE% -DargLine=-javaagent:%SELENIC_PATH%/selenic_agent.jar=captureDom=true,screenshot=failures -Dwebdriver.chrome.driver="%CHROME_DRIVER_PATH%" -Dbase.url="%BASE_URL%" -Ddotnet.home="%DOTNET_HOME%" -Ddottest.install.dir="%DOTTEST_INSTALL_DIR%" -Dcpptest.std.install.dir="%CPPTEST_STD_INSTALL_DIR%" -Dcpptest.pro.install.dir="%CPPTEST_PRO_INSTALL_DIR%" -Ddottest.project.git.url="%DOTTEST_PROJECT_GIT_URL%" -Djtest.project.git.url="%JTEST_PROJECT_GIT_URL%" -Dcpptest.std.project.git.url="%CPPTEST_STD_PROJECT_GIT_URL%" -Dcpptest.pro.project.git.url="%CPPTEST_PRO_PROJECT_GIT_URL%"
```
#### Parameters in command line:
* **-Dtest** (Required): Which test cases you want to run. 
  * Options: `CpptestStdProjectTests`, `CpptestProProjectTests`,`DottestProjectTests`, `JtestProjectTests`, `* (run all tests)`
* **-Dwebdriver.chrome.driver** (Required): The full path of the Chrome browser driver.
* **-Dbase.url** (Required): The home page of jenkins, eg: `http://localhost:8080/jenkins/`
* Whether the following parameters should be required depends on the language of tested project:
    * **-Ddottest.project.git.url** (Optional): The git repository for dotTest example project.
    * **-Djtest.project.git.url** (Optional): The git repository for jtest example project.
    * **-Dcpptest.std.project.git.url** (Optional): The git repository for cpptest standard example project.
    * **-Dcpptest.pro.project.git.url** (Optional): The git repository for cpptest professional example project.
* **-Dsettings.path** (Required): The path to the settings file of Parasoft tools for tested project.
* For [Selenic Agent Options](https://docs.parasoft.com/display/SEL20222/Command+Line#CommandLine-SelenicAgentOptions)
