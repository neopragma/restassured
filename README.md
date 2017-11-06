# RestAssured sandbox

RestAssured or Cucumber-JVM?

## RestAssured pros

* No need to URL encode strings to be passed on HTTP commands; handled under the covers
* Fluid API may be appealing if all you want is test automation without Specification by Example

## RestAssured cons

* No direct support for parsing Gherkin; must use an additional tool (such as Cucumber...hmm)
* Fluid API makes it hard to extract reusable logic in between RestAssured method calls
* Use of annotations leads to method names that can be either redundant or not very expressive of intent; cluttered code

## Cucumber pros

* More readable (in my opinion); Lambda-style stepdefs are concise, clean
* Easier to structure code for reusability because you can insert calls to any method in the midst of stepdefs (because it isn't a fluid API)
* No need to learn and support multiple tools; Cucumber handles both Gherkin parsing and step automation

## Cucumber cons

* Requires custom JUnit runner (minor inconvenience)