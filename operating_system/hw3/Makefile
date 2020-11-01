CC = gcc
CFLAGS = -g

TARGET = index

all: $(TARGET)

index: lib/process.o lib/queue.o lib/timeline.o index.o
	$(CC) $(CFLAGS) -o $@ $@.o process.o queue.o timeline.o

lib/process.o:
	$(CC) $(CFLAGS) -c lib/process.c

lib/queue.o:
	$(CC) $(CFLAGS) -c lib/queue.c

lib/timeline.o:
	$(CC) $(CFLAGS) -c lib/timeline.c

index.o:
	$(CC) $(CFLAGS) -c index.c

clean:
	rm -rf *.o
	rm -rf $(TARGET)