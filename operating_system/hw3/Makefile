CC = gcc
CFLAGS = -g

LIBRARY = ./lib/

TARGET = index

all: $(TARGET)

index: $(LIBRARY)process.o $(LIBRARY)queue.o $(LIBRARY)timeline.o index.o
	$(CC) $(CFLAGS) -o $@ $@.o process.o queue.o timeline.o

$(LIBRARY)process.o:
	$(CC) $(CFLAGS) -c $(LIBRARY)process.c

$(LIBRARY)queue.o:
	$(CC) $(CFLAGS) -c $(LIBRARY)queue.c

$(LIBRARY)timeline.o:
	$(CC) $(CFLAGS) -c $(LIBRARY)timeline.c

index.o:
	$(CC) $(CFLAGS) -c index.c

clean:
	rm -rf *.o
	rm -rf $(TARGET)