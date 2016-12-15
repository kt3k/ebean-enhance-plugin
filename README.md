# ebean-enhance-plugin v3.0.0

> A gradle plugin for enhancing ebean entities.

# Usage

```
plugins {
  id 'java'
  id 'com.github.kt3k.ebean.enhance' version '3.0.0'
}

ebeanEnhance {
  packages = 'com.example.models.**'
}
```

The above automatically enhances the ebean models under the `com.example.models` package.

More technically, `com.github.kt3k.ebean.enhance` plugins creates `ebeanEnhace` task in your project and hook it to `classes` task of `java` plugin.

You can specify following properties in your build.gradle:

## packages

The package to enhance (required)

## agentVersion

The version of `org.avaje.ebeanorm:avaje-ebeanorm-agent` module (default `4.9.1`)

## transformArgs

The transformation arguments to pass to `AntEnhanceTask` (default `debug=5`)

# License

MIT

# History

- 2016-04-17   v2.0.5   Enhance classes under the test directory.
