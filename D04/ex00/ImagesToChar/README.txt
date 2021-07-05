mkdir target && mkdir target/edu.school21.printer
javac -d target/edu.school21.printer src/java/edu.school21.printer/app/Main.java src/java/edu.school21.printer/logic/PrintBmpImage.java
java -cp target/edu.school21.printer Main it.bmp
rm -fr target