mkdir target && mkdir target/resources;
javac -d target/ src/java/edu/school21/printer/app/Main.java src/java/edu/school21/printer/logic/PrintBmpImage.java &&
cp src/resources/it.bmp target/resources &&
jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target ./edu/school21/printer &&
java -jar ./target/images-to-chars-printer.jar