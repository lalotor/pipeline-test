node {
  try {
    def mvnHome
    stage('Preparation') {
      //git url: 'https://github.com/lalotor/pipeline-test.git', branch: 'develop'
	  checkout scm
      mvnHome = tool 'Maven'
    }
    stage('Unit tests') {
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' test"
      } else {
         bat(/"${mvnHome}\bin\mvn" test/)
      }
    }
    stage('Analysis') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -batch-mode -V -U -e pmd:pmd pmd:cpd"
      } else {
         bat(/"${mvnHome}\bin\mvn" --batch-mode -V -U -e checkstyle:checkstyle pmd:pmd pmd:cpd findbugs:findbugs spotbugs:spotbugs/)
      }
	  
      recordIssues enabledForFailure: true, tools: [mavenConsole(), java(), javaDoc()]
      recordIssues enabledForFailure: true, tool: checkStyle()
      recordIssues enabledForFailure: true, tool: spotBugs()
      recordIssues enabledForFailure: true, tool: cpd(pattern: '**/target/cpd.xml')
      recordIssues enabledForFailure: true, tool: pmd(pattern: '**/target/pmd.xml')
	  
	  //recordIssues enabledForFailure: true, aggregatingResults: true, tool: checkStyle(pattern: 'checkstyle-result.xml')
	  
	  // recordIssues tool: enabledForFailure: true, java(), unstableTotalHigh: 10, unstableNewAll: 1
      
      //def pmd = scanForIssues tool: pmd(pattern: '**/target/pmd.xml')
      //publishIssues issues: [pmd]
      
      //publishIssues id: 'analysis', name: 'All Issues', 
      //  issues: [pmd], 
      //  filters: [includePackage('io.jenkins.plugins.analysis.*')]
    }
    stage('Build') {
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
    }
    stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      //archive 'target/*.jar'
    }
  } catch (e) {
	currentBuild.result = "FAILED"
	echo 'This will run only if failed'
	
    //*** ENVIAR NOTIFICACION DE ERROR A INTERESADOS ***
	/*def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    def details = """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
      <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>"""	
	emailext (
      subject: subject,
      body: details,
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )*/
	
    throw e
  } finally {
    def currentResult = currentBuild.result ?: 'SUCCESS'
    if (currentResult == 'UNSTABLE') {
        echo 'This will run only if the run was marked as unstable'
    }

    def previousResult = currentBuild.previousBuild?.result
    if (previousResult != null && previousResult != currentResult) {
        echo 'This will run only if the state of the Pipeline has changed'
        echo 'For example, if the Pipeline was previously failing but is now successful'
    }

    echo 'This will always run'
  }
}