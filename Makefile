CSC = mcs

TARGET = csjosa.exe

default:
	$(CSC) -out:$(TARGET) main.cs csjosa.cs

clean:
	rm -rf $(TARGET)

