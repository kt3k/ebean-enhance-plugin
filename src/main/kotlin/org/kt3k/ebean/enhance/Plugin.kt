package org.kt3k.ebean.enhance

import org.gradle.api.*

public class Plugin : org.gradle.api.Plugin<Project> {

  val EXTENSION_NAME = "ebeanEnhance"
  val CONFIGURATION_NAME = "ebeanEnhance"
  val ANT_TASK_NAME = "enhanceEbean"
  val TASK_NAME = "enhanceEbean"

  override fun apply(project: Project) {

    project.extensions.create(EXTENSION_NAME, Extension::class.java)

    project.configurations.create(CONFIGURATION_NAME)
    project.dependencies.add(CONFIGURATION_NAME, ext(project).agentGroupId + ":" + ext(project).agentArtifactId + ":" + ext(project).agentVersion)

    val task = project.task(TASK_NAME)

    task.doLast(object: Action<Task> {

      override fun execute(task: Task) {

        project.ant.invokeMethod("taskdef", mapOf(
          "name" to ANT_TASK_NAME,
          "classname" to ext(project).antEnhanceTaskClassName,
          "classpath" to project.configurations.getByName(CONFIGURATION_NAME).asPath
        ))

        project.ant.invokeMethod(ANT_TASK_NAME, mapOf(
          "classSource" to project.buildDir.absolutePath + ext(project).classFilePath,
          "packages" to ext(project).packages,
          "transformArgs" to ext(project).transformArgs
        ))

      }

    })

    (project.property("classes") as Task).dependsOn(task)

  }

  private fun ext(project: Project): Extension {
    return project.extensions.getByName(EXTENSION_NAME) as Extension
  }

}
