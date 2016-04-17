package org.kt3k.ebean.enhance

import org.gradle.api.*

const val EXTENSION_NAME = "ebeanEnhance"
const val CONFIGURATION_NAME = "ebeanEnhance"
const val ANT_MAIN_TASK_NAME = "enhanceEbeanMain"
const val ANT_TEST_TASK_NAME = "enhanceEbeanTest"
const val MAIN_TASK_NAME = "enhanceEbeanMain"
const val TEST_TASK_NAME = "enhanceEbeanTest"
const val MAIN_CLASS_FILE_PATH = "/classes/main"
const val TEST_CLASS_FILE_PATH = "/classes/test"

fun ext(project: Project): Extension {
  return project.extensions.getByName(EXTENSION_NAME) as Extension
}

public class Plugin : org.gradle.api.Plugin<Project> {

  override fun apply(project: Project) {

    project.extensions.create(EXTENSION_NAME, Extension::class.java)

    project.configurations.create(CONFIGURATION_NAME)
    project.dependencies.add(CONFIGURATION_NAME, ext(project).agentGroupId + ":" + ext(project).agentArtifactId + ":" + ext(project).agentVersion)

    val taskMain = project.task(mapOf("type" to EnhanceEbeanTask::class.java), MAIN_TASK_NAME)
    val taskTest = project.task(mapOf("type" to EnhanceEbeanTask::class.java), TEST_TASK_NAME)

    taskMain.setProperty("antTaskName", ANT_MAIN_TASK_NAME)
    taskTest.setProperty("antTaskName", ANT_TEST_TASK_NAME)

    taskMain.setProperty("classFilePath", MAIN_CLASS_FILE_PATH)
    taskTest.setProperty("classFilePath", TEST_CLASS_FILE_PATH)

    (project.property("classes") as Task).dependsOn(taskMain)
    (project.property("testClasses") as Task).dependsOn(taskTest)

    taskMain.dependsOn((project.property("compileJava") as Task))
    taskTest.dependsOn((project.property("compileTestJava") as Task))

  }

}
