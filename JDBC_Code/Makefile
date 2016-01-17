JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	RetailDriver.java \
 	RetailController.java \
	RetailMain.java \
	ShowMenu.java \
	RetailConnect.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
