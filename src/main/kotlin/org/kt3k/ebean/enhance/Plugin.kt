package org.kt3k.ebean.enhance

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.JavaPlugin

const val EXTENSION_NAME = "ebeanEnhance"
const val CONFIGURATION_NAME = "ebeanEnhance"
const val ANT_MAIN_TASK_NAME = "enhanceEbeanMain"
const val ANT_TEST_TASK_NAME = "enhanceEbeanTest"
const val MAIN_TASK_NAME = "enhanceEbeanMain"
const val TEST_TASK_NAME = "enhanceEbeanTest"
const val MAIN_CLASS_FILE_PATH = "/classes/main"
const val TEST_CLASS_FILE_PATH = "/classes/test"

fun ext(project: Project): Extension {
  return project.extensions.getByType(Extension::class.java)
}

public class Plugin : org.gradle.api.Plugin<Project> {

  override fun apply(project: Project) {

    project.apply(mapOf("plugin" to JavaPlugin::class.java))

    project.extensions.create(EXTENSION_NAME, Extension::class.java)

    project.configurations.create(CONFIGURATION_NAME)

    project.afterEvaluate({
      project.repositories.mavenCentral()
      project.repositories.jcenter()
      project.dependencies.add(CONFIGURATION_NAME, ext(project).agentGroupId + ":" + ext(project).agentArtifactId + ":" + ext(project).agentVersion)
    })

    val taskMain = project.tasks.create(MAIN_TASK_NAME, EnhanceEbeanTask::class.java)
    val taskTest = project.tasks.create(TEST_TASK_NAME, EnhanceEbeanTask::class.java)

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
