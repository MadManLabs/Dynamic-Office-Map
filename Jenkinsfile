node {
	def mvn
	
	stage('Preparation') {
		checkout scm
		
		def mvnHome = tool 'M3'
		mvn = "${mvnHome}/bin/mvn"
	}
	
	stage('Build Server') {
		sh "${mvn} -f DOMServer/server/pom.xml clean package -U"
	}
	
	stage('Build Web Client') {
		sh "${mvn} -f DOMServer/web-client/pom.xml clean package -U"
	}
	
}