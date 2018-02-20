FILES := $(shell find . -name "*.java")
main: compile exec
compile:
	@echo 'Compiling..'
	@javac -d bin $(FILES) -Xlint:unchecked
	@jar -cvmf manifest.txt candroid.jar -C bin . > /dev/null
exec:
	@echo 'Executing..'
	@java -jar candroid.jar
