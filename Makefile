JAVA_COMPILER = javac
JVM = java
BIN = build/classes
LIB = "dist/lib/jakarta.xml.bind-api.jar:dist/lib/jaxb-impl.jar"
SOURCES = @sources.txt
MANIFEST = manifest.txt
SRC_DIR = src
JAR_NAME = dist/RYMViewer.jar

all: clean compile jar run

compile:
	@echo "Compilant els fitxers Java..."
	@mkdir -p $(BIN)
	$(JAVA_COMPILER) -d $(BIN) -cp $(LIB) $(SOURCES)

jar:
	@echo "Generant l'arxiu JAR..."
	jar -cvfm $(JAR_NAME) $(MANIFEST) -C $(BIN) .

run:
	@echo "Executant l'aplicació..."
	$(JVM) -jar $(JAR_NAME)	

clean:
	@echo "Netejant binaris antics..."
	@rm -rf $(BIN)
	@rm -f $(JAR_NAME)
