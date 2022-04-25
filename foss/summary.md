# Lecture Summary

## Redirection

### STDIO
    stdin: 0<
    stdout: 1>
    stderr: 2>

### PIPE
    |
    |&

### EXECUTE
    xargs
    `cmd`


## BASH
### Assign String
```bash
$ name="Hwanyong Lee"
$ echo "Hello $name!"
Hello Hwanyong Lee!
$ echo 'Hello $name!'
Hello $name!
```

### Assign Number (int only)
```bash
$ a=200; b=300;
$ echo $(($a+$b))
500
$ if ((a>b)); then echo "A is big"; fi
A is big
```

### Brace expansion
```bash
$ echo {A,B,C}.js
A.js B.js C.js
$ echo {1..5}1 2 3 4 5
$ echo {1..10..2}
1 3 5 7 9
```

### Shell execution
```bash
$ echo "Im in $(pwd)"
Im in /home/hwan
$ echo "Im in `pwd`"
Im in /home/hwan
```

### Condition
```bash
 string=""
 if [[ -z "$string" ]]; then
> echo "string is empty"
> elif [[ -n "$string" ]]; then
> echo "string is not empty"
> fi
string is empty
```

## Shell script

`# Start with #!/bin/bash`

`# is for comment`
```bash
$ # This is comments!
$
```

### Meaning of () [] {} (()) [[]]
    () - group exec in another subshell
    {} - group exec in same shell
    [[ ]]  - condition, return 0 or 1
    (( )) - Numerical

### Sequence of command
    cmd1 ; cmd 2
    cmd1 && cmd2  # Conditional AND
    cmd1 || cmd2  # Conditional OR

## Wild card
### selection in working directory

### Star or Asterisk (*) 
    *  - All files
    a* - All files start with a 
    *a* - All files including a

### Question Mark (?)
    ??? - All files with three characters
    a?? - All files with three characters start with a
    ??a - All files with three characters start with a

### Square Brackets ([])
    [abc] - a or b or c
    [a-c0-3] - (from a to c) or (from 0 to 3)
    [ab][01]* - All files start character is a or b, second character is 0 or 1 

## Strings

### Substrings
    ${FOO:0:3}		Substring (position, length)
    ${FOO:(-3):3}	Substring from the right

### String Substitution
    ${FOO%suffix}	   Remove suffix
    ${FOO#prefix}	   Remove prefix
    ${FOO%%suffix}	   Remove long suffix
    ${FOO##prefix}	   Remove long prefix
    ${FOO/from/to}	   Replace first match
    ${FOO//from/to}	   Replace all
    ${FOO/%from/to}	   Replace suffix
    ${FOO/#from/to}	   Replace prefix

### String Length
    ${#FOO}		Length of $FOO

### Default Value
    ${FOO:-val}	$FOO, or val if unset (or null)
    ${FOO:=val}	Set $FOO to val if unset (or null)
    ${FOO:+val}	val if $FOO is set (and not null)
    ${FOO:?message}  Show error message and exit. if $FOO is unset (or null)


### Examples
    STR="/path/to/foo.cpp"
    echo ${STR%.cpp}    	# /path/to/foo
    echo ${STR%.cpp}.o  	# /path/to/foo.o
    echo ${STR%/*}      	# /path/to

    echo ${STR##*.}     	# cpp (extension)
    echo ${STR##*/}     	# foo.cpp (basepath)

    echo ${STR#*/}      	# path/to/foo.cpp
    echo ${STR##*/}     	# foo.cpp

    echo ${STR/foo/bar} 	# /path/to/bar.cpp
    STR="Hello world"
    echo ${STR:6:5}   	# "world"
    echo ${STR: -5:5}  	# "world"

    SRC="/path/to/foo.cpp"
    BASE=${SRC##*/}   	#=> "foo.cpp" (basepath)
    DIR=${SRC%$BASE}  	#=> "/path/to/" (dirpath)

## Loop & Function

### Basic loop
```bash
for i in /etc/rc.*; do
  echo $i
done
```

### C-like for loop
```bash
for ((i = 0 ; i < 100 ; i++)); do
  echo $i
done
```

### Ranges loop
```bash
for i in {5..50..5}; do
    echo "Welcome $i"
done
```

### Read Line loop
```bash
cat file.txt | while read line; do
  echo $line
done
```

### Function Definition
```bash
myfunc() {
    echo "hello $1"
}
```

### Same as above (alternate syntax)
```bash
function myfunc() {
    echo "hello $1"
}
myfunc "John"
```

### Argument
    $#	Number of arguments
    $*	All postional arguments (as a single word)
    $@	All postitional arguments (as separate strings)
    $1	First argument
    $_	Last argument of the previous command

## Conditions

### Exec condition
```bash
if ping -c 1 google.com; then
> echo "working internet connection"
> fi
```

### Condition
    [[ -z STRING ]]	Empty string
    [[ -n STRING ]]	Not empty string
    [[ STRING == STRING ]]	Equal
    [[ STRING != STRING ]]	Not Equal
    [[ NUM -eq NUM ]]	Equal
    [[ NUM -ne NUM ]]	Not equal
    [[ NUM -lt NUM ]]	Less than
    [[ NUM -le NUM ]]	Less than or equal
    [[ NUM -gt NUM ]]	Greater than
    [[ NUM -ge NUM ]]	Greater than or equal
    [[ STRING =~ STRING ]]	Regexp
    (( NUM < NUM ))	Numeric conditions

### More conditions
    [[ -o noclobber ]]	If OPTIONNAME is enabled
    [[ ! EXPR ]]	Not
    [[ X && Y ]]	And
    [[ X || Y ]]	Or

### File Condition
    [[ -e FILE ]]	Exists
    [[ -r FILE ]]	Readable
    [[ -h FILE ]]	Symlink
    [[ -d FILE ]]	Directory
    [[ -w FILE ]]	Writable
    [[ -s FILE ]]	Size is > 0 bytes
    [[ -f FILE ]]	File
    [[ -x FILE ]]	Executable
    [[ FILE1 -nt FILE2 ]]	1 is more recent than 2
    [[ FILE1 -ot FILE2 ]]	2 is more recent than 1
    [[ FILE1 -ef FILE2 ]]	Same files

## Arrays And Dictionary

### Define Array
```bash
Fruits=('Apple' 'Banana' 'Orange')
Fruits[0]="Apple"
Fruits[1]="Banana"
Fruits[2]="Orange"
```

### Operation
```bash
Fruits=("${Fruits[@]}" "Melon") # Push
Fruits+=('Watermelon')          # Also Push
Fruits=( ${Fruits[@]/Ap*/} )    # Remove by regex match
unset Fruits[2]                 # Remove one item
Fruits=("${Fruits[@]}")         # Duplicate
Fruits=("${Fruits[@]}" "${Veggies[@]}") # Concatenate
lines=(`cat "logfile"`)         # Read from file# Interation
for i in "${arrayName[@]}"; do
  echo $i
done
```

### Define Dictionary
```bash
declare -A sounds
sounds[dog]="bark"
sounds[cow]="moo"
sounds[bird]="tweet"
sounds[wolf]="howl"
```

### Example
```bash
echo ${sounds[dog]} # Dog's sound
echo ${sounds[@]}   # All values
echo ${!sounds[@]}  # All keys
echo ${#sounds[@]}  # Number of elements
unset sounds[dog]   # Delete dog
```

### Iteration over value
```bash
for val in "${sounds[@]}"; do
  echo $val
done
```

### Iterate over keys
```bash
for key in "${!sounds[@]}"; do
  echo $key
done
```

## Options & Glob Options

### Options
```bash
set -o noclobber  # Avoid overlay files (echo "hi" > foo)
set -o errexit    # Used to exit upon error, avoiding cascading errors
set -o pipefail   # Unveils hidden failures
set -o nounset    # Exposes unset variables
```

### Glob options
```bash
shopt -s nullglob    # Non-matching globs are removed  ('*.foo' => '')
shopt -s failglob    # Non-matching globs throw errors
shopt -s nocaseglob  # Case insensitive globs
shopt -s dotglob     # Wildcards match dotfiles ("*.sh" => ".foo.sh")
shopt -s globstar    # Allow ** for recursive matches ('lib/**/*.rb' => 'lib/a/b/c.rb')
```

## History

### Command
```bash
history		            # Show history
shopt -s histverify	    # Don’t execute expanded result immediately
```
### Operation
    !!	                Execute last command again
    !!:s/<from>/<TO>/	Replace first <from> to <to>
    !!:gs/<from>/<TO>/	Replace all <from> to <to>
    ^<from>^<to>	    Replace first <from> to <to>
    !$:t	            Expand only basename from last parameter
    !$:h	            Expand only directory from last parameter

### Expansions
    !$	        Expand last parameter of most recent command
    !*	        Expand all parameters of most recent command
    !-n	        Expand nth most recent command
    !n	        Expand nth command in history
    !<com>	    Expand most recent invocation of command

### Slices
    !!:n	    Expand only nth token from most recent command (command is 0; first argument is 1)
    !^	        Expand first argument from most recent command
    !$	        Expand last token from most recent command
    !!:n-m	    Expand range of tokens from most recent command
    !!:n-$	    Expand nth token to last from most recent command
    !!          can be replaced with any valid expansion i.e. !cat, !-2, !42, etc.

## Misc 1

### Numerical calculation
```bash
$ echo $((a + 200))      # Add 200 to $a
$ echo $(($RANDOM%200))  # Random number 0..199
```

### Command check
```bash
$ which ls 
$ command -v ls 
$ where ls
```

### Trap errors
```bash
trap 'echo Error at about $LINENO' ERR
```
or
```bash
traperr() {
  echo "ERROR: ${BASH_SOURCE[1]} at about ${BASH_LINENO[0]}"
}
```
```bash
set -o errtrace
trap traperr ERR
```

### Subshells
```bash
$ (cd somdir ; echo "I'm in now $PWD")
$ pwd
```


### Printf
```bash
$ printf "Hello %s, Im %s" Sven Olga
Hello Sven, Im Olga $Prompt
$ printf "1 + 1 = %d\n"
1 + 1 = 2
$ printf "This is how you print a float: %f\n" 2
"This is how you print a float: 2.000000"
```

### Special Variable
    $?	Exit status of last task
    $!	PID of last background task
    $$	PID of shell
    $0	Filename of the shell script

## Misc 2

### directory
```bash
$ cd
$ pushd path	# push directory and cd
$ popd		    # pop from $DIRSTACK and cd
$ cd ~  		# go to home directory
$ cd ~user		# go to user's home	
$ cd ..
```

### Heredoc
```bash
$ cat <<END
> Hello
> END
# Here Strings
$ ./a.out <<<"200 300 400"
# Reading Input
echo -n "Proceed? [y/n]: "
read ans
echo $ans
read -n 1 ans
```

### Redirection
    [n]<file		redirecting input
    [n]>file		redirecting output 
    [n]>|file 		output ignore noclobber

### Redirection of stdout/stderr
    &>file , >&file  , >file 2>&1

### Appending
    &>>file , &>>file , >>file 2&1

### duplicating file descriptors
    [n]<&file   or   [n]>&file

### Moving file descriptors
    [n]<&[m]   or   [n]>&[m]

### Open file descriptors for RW
    [n]<>file

### example
    (3> outfile   2>&1   1>&3   date -@ )  > errfile



