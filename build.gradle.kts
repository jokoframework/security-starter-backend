import java.util.*

plugins {
	java
	id("org.springframework.boot") version "2.7.12"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

val artifactoryProps = Properties()
File("src/main/resources/artifactory.properties")
		.inputStream().use { artifactoryProps.load(it) }

group = "io.github.jokoframework"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	mavenLocal()
	maven { url = uri("https://repo.maven.apache.org/maven2/") }
	maven{ url = uri(artifactoryProps.getProperty("artifactory.url")) }
	maven { url = uri("https://jitpack.io") }
}

dependencies {

	//Spring boot dependencies
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	//BD Postgres dependency
	implementation ("org.postgresql:postgresql:42.6.0")

	//Logger dependencies
	implementation ("org.apache.logging.log4j:log4j-api:2.20.0")
	implementation ("org.apache.logging.log4j:log4j-core:2.20.0")

	//Joko-security dependency
	implementation("io.github.jokoframework:joko-security:1.1.9")

	implementation ("com.google.code.gson:gson:2.8.8")
	implementation ("com.auth0:java-jwt:3.18.1")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
