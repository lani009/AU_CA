Ts = 1/100;
N = 100;
t = [0:N-1]*Ts;
xmax = 1;
xmin = -1;
L = 8;

noise = 0.51; A = 0.5;
delta = (xmax-xmin)/L;
q_boundary = xmin + [0:L]*delta;
q_level = q_boundary(1:L)+delta/2;
code_table = [0 0 0; 0 0 1; 0 1 0; 1 0 0; 1 0 1; 0 1 1; 1 1 0; 1 1 1];
transmitted = [];

for n=1:N
    x(n) = sin(2*pi*(n-1)*Ts);
    [x_q(n), code] = A2D(x(n), q_boundary, q_level,code_table); % 균일 양자화, 부호화
    for m = 1:length(code)
        y(m) = A*code(m)+noise*(rand-0.5); % 에러 더하는 과정
        y_q(m) = (y(m) > A/2);
    end
    transmitted = [transmitted code(1:length(code))];
    y_demo(n) = q_level(strmatch(y_q,code_table)); % 복조 과정
end

symbol_err_rate = sum(y_demo~=x_q)/N;

fprintf("Symbol error rate = %f\n", symbol_err_rate);
plot(t, x, 'k', t, y_demo, 'r')