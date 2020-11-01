CC = gcc
DEPENDENT = lib/process.c lib/queue.c lib/timeline.c

TARGET = index

all: $(TARGET)

index:
	$(CC) -o $@ $@.c $(DEPENDENT)

clean:
	rm -f index