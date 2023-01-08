# Clinder
## CLI for Java

```java
// Serve the application for arguments
Clinder.serve(new MyApp)

// My app
class MyApp extends ClinderApp {
    public void run(ClinderArgs args) {
        // Gets argument by name or default value
        args.getFirst("name", "unnamed");
        
        // Gets list of argument values
        args.getList("users");
        
        // Is argument present
        args.isPresent("name");
    }

    default void help(ClinderArgs args) {
        // Here you can show output if -h or --help is present
    }
}
```