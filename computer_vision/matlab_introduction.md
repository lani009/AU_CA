# Matlab Introduction

## 1. Basic Operation

1. 5+6
2. 3-2
3. 5*8
4. 1/2
5. 2^7

## 2. Logical Operation

1. 1 == 1
2. 1 ~= 2
3. 1 && 0
4. 1 || 0
5. xor (1,0)

## 3. Assignment

1. a = 3
2. a = 3; % semicolon suppressing output
3. b = 'hi'; % string
4. c = (3>=1)
5. a = pi;

## 4. Display

1. disp(a)
2. disp(sprintf('2 decimal : %0.2f', a)

## 5. Matrix Assignment and generating function

1. A = [ 1 2 ; 3 4 ; 5 6 ]; % matrix
2. V = [ 1 2 3 ];
3. V = [ 1 ; 2 ; 3 ];
4. % increase number
5. V = 1:0.1:2 %1 1.1 1.2 ... 2
6. V = 1:6 % 1 2 3 .. 6
7. % generating matrix by functions
8. ones(2,3)
9. C = 2*ones(2,3)
10. C = [ 2 2 2 ; 2 2 2];
11. W = ones(1,3)
12. W = zeros(1,3)
13. W = rand(1,3)
14. %difference between rand and randn
15. W = rand(3,3)
16. W = randn(3,3)
17. % identical matrix
18. I = eye(4)

## 6. histogram and example equation

1. W = -6 + sqrt(10)*(randn(1,1000));
2. hist(w)
3. hist(w, 50)

## 7. Help and search instructions

1. help eye
2. lookfor eye

## 8. Matrix size

1. A=[1 2; 3 4; 5 6];
2. sz = size(A)
3. size(A)
4. size(A,1)
5. size(A,2)
6. V= [1 2 3 4];
7. length(V)
8. length(A) % longer dimension

## 9. System command: e.g., save and load

1. pwd
2. cd 'path'
3. ls
4. load a.mat
5. load('a.mat')
6. whos
7. clear V
8. V = 1:1:10;
9. save V.mat V
10. save V.txt V -ascii % save as text


## 10. Matrix representation

1. A = [1 2; 3 4; 5 6];
2. A(3,2)
3. A(2,:) % : means every element along that row/column
4. A(:,2)
5. A([1 3], :)
6. A(:,2) = [10; 11; 12]
7. A= [A [100; 101; 102]]
8. % matrix concatenation
9. A = [1 2; 3 4; 5 6];
10. B = [11 12; 13 14; 15 16];
11. C = [A B]
12. C = [A; B]

## 11. Computing data by element-wise operation and others

1. A = [1 2 ; 3 4; 5 6]
2. B = [11 12; 13 14; 15 16]
3. C = [1 1; 2 2]
4. A*C
5. A .* B % element wise multiplication
6. A .^ 2
7. V = [1;2;3]
8. 1 ./ V
9. 1 ./ A
10. log(V)
11. exp(V)
12. abs([-1;-2;-3])
13. -V % negative V
14. V + ones(length(V), 1)
15. V + 1

## 12. Well-known operations (e.g., max, find, floor, ..)

1. A=[1 2 ; 3 4; 5 6]
2. A' % transpose
3. (A')'
4. a = [1 15 2 0.5]
5. val = max(a)
6. [val, ind] = max(a) % value and index
7. max(A)
8. a < 3 % element-wise comparison
9. find(a<3)
10. A = magic(3)
11. [c,r] = find( A >= 7 )
12. A(3,2) %check output
13. sum(a)
14. floor(a)
15. ceil(a)

## 13. Max and Sum element-wise operations

1. A=magic(3)
2. max(A, [], 1)
3. max(A, [], 2)
4. max(A)
5. max(max(A))
6. A(:)
7. max(A(:))
8. %
9. A=magic(9)
10. sum(A,1)
11. sum(A,2)
12. sum(sum(A))
13. sum(A(:))

## 14. Matrix inversion

1. A = magic(3)
2. pinv(A)
3. inv(A)
4. temp = pinv(A)*A

## 15. Plotting Data

1. t=[0:0.1:0.98];
2. y1 = sin(2*pi*4*t);
3. plot(t, y1);
4.
5. y2 = cos(2*pi*4*t);
6. plot(t, t2);
7.
8. plot(t, y1);
9. hold on
10. plot(t, y2, 'r');
11. xlabel('time');
12. ylabel('value');
13. legend('sin', 'cos')
14. title('my plot')
15. print -dpng 'myplot.png'
16. close % close figure window
17. close all % close all figure windows
18. % subplot
19. subplot(1,2,1); % divides plot a 1x2 grid, access first element
20. plot(t, y1);
21. subplot(1,2,2);
22. plot(t, y2);
23. axis([0.5 1 -1 1]);
24. clf;

## 16. Colormap

1. A = magic(5);
2. imagesc(A)
3. colorbar, colormap gray;
17. Control Statements (for, while, if )
1. % simple for
2. v=zeros(10,1)
3. for i=1:10,
4. v(i) = 2^i;
5. end;
6. v
7.
8. % index-wise for
9. indices=1:10
10. for i=indices,
11. disp(i);
12. end;
13.
14. % while
15. i=1;
16. while i<= 5
17. v(i) = 100;
18. i = i+ 1;
19. end;
20. v;
21.
22. % if statement
23. i=1;
24. while true;
25. v(i) = 999;
26. i=i+1;
27. if i == 6,
28. break;
29. end;
30. end;

## 18. If statement

1. v = 1;
2. if v==1,
3. disp('value is 1');
4. elseif v==2,
5. disp('value is 2');
6. else
7. disp('value is not 1 and 2');
8. end;

## 19. Function: saved ‘funtion name.m’ file before calling

### 19.1. Fuction 1

1. % function
2. function y = squareThisNumber(x)
3.
4. y = x^2;

### 19.2. Function 2

1. % function
2. function [y1, y2] = squareThisNumber(x)
3.
4. y1 = x^2;
5. y2 = x^3;

## 20.  J function example

### 20.1. File: costFunctionJ.m

1. function J = costFunctionJ(X, y, theta)
2.
3. % x is the "design matrix" containing our training examples.
4. % y is the class labels
5.
6. m = size(X, 1); % number of training examples
7. predictions = X*theta; % predictions of hypothesis on all m examples
8. sqrErrors = (predictions - y).^2; % square erros
9.
10. J = 1/(2*m) * sum(sqrErrors);

### 20.2. Execution

1. X=[1 1 ; 1 2 ; 1 3];
2. y=[1; 2; 3];
3. theta=[0;1];
4. j = costFunctionJ(X,y, theta)
5. theta=[0;0];
6. j = costFunctionJ(X,y, theta)

## 21. Vectorization

The equation is as follows;
h𝜃
(𝑥) = ∑ 𝜃𝑗𝑥𝑗
𝑛
𝑗=0 = 𝜃
𝑇𝑥 θ = [
𝜃0
𝜃1
𝜃2
] 𝑥 = [
𝑥0
𝑥1
𝑥2
]

Unvectorized implementation

1. prediction = 0.0;
2. for j = 1:n+1,
3. prediction = prediction + theta(j) * x(j);
4. end;

* Vectorized implementation

1. prediction = theta' * x;
** End of this material
