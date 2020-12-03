CC = gcc
CFLAGS = -g -lpthread

TARGET = index

all: $(TARGET)

index: index.o
	$(CC) -o $@ $@.o $(CFLAGS)

index.o:
	$(CC) -c index.c $(CFLAGS)

clean:
	rm -f index index.o