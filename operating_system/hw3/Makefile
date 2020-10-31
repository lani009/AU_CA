CC = gcc
OBJECTS = lib/process.c lib/queue.c lib/timeline.c

TARGET = index

all: $(TARGET)

index:
	$(CC) -o $@ $@.c $(OBJECTS)

clean:
	rm -f index