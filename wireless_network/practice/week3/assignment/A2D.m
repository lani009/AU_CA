function [q_sam, code] = A2D(x, q_boundary, q_level, code_table)

N = length(q_level);

Nq = length(x);

for n = 1:Nq
    I = find(x(n) < q_boundary(2:N));
    if ~isempty(I)
        q_sam(n) = q_level(I(1));
        code(n,:) = code_table(I(1),:);
    else
        q_sam(n) = q_level(N);
        code(n,:) = code_table(N,:);
    end
end