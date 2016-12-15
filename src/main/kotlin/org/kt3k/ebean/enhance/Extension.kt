package org.kt3k.ebean.enhance

/**
 * The plugin extension.
 */
public open class Extension {

  /**
   * The packages to enhance.
   *
   * ex. "com.exmaple.models.**"
   */
  var packages = ""

  /**
   * Ebean enhance agent group id.
   */
  var agentGroupId = "io.ebean"

  /**
   * Ebean enhance agent artifact id.
   */
  var agentArtifactId = "ebean-agent"
  /**
   * Ebean enhance agent version.
   */
  var agentVersion = "10.1.1"

  /**
   *
   */
  var antEnhanceTaskClassName = "io.ebean.enhance.ant.AntEnhanceTask"

  /**
   * Transoformation args for enhance agent.
   */
  var transformArgs = "debug=5"

}
