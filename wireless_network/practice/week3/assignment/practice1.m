Ts = 1/50;
N = 100;
t = [0:N-1]*Ts;
xmax = 1;
xmin = -1;
L = 4;

delta = (xmax-xmin)/L;
q_boundary = xmin + [0:L]*delta;
q_level = q_boundary(1:L)+delta/2;
code_table = [0 0; 0 1; 1 0; 1 1];

Nq = length(q_level);

for n = 1:N
    x(n) = sin(2*pi*(n-1)*Ts);
    
    I = find(x(n) < q_boundary(2:Nq));
    if ~isempty(I)
        q_sam(n) = q_level(I(1));
        code(n,:) = code_table(I(1),:);
    else
        q_sam(n) = q_level(Nq);
        code(n,:) = code_table(Nq,:);
    end
end

plot(t, x, 'k', t, q_sam, 'r');