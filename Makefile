# Compiler
JC = javac

# Flags
JFLAGS = -g

# Target
TARGET = CoffeePayment

# Source files
SRCS = CoffeePayment.java

# Classpath
CLASSPATH =

# Main target
default: classes

# Compiling
classes:
	$(JC) $(JFLAGS) $(SRCS)

# Running
run: classes
	java $(CLASSPATH) $(TARGET)

# Clean
clean:
	$(RM) *.class
