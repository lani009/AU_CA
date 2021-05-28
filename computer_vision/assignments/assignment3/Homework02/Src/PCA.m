% PCA   Principal Component Analysis.
% [P,D]=PCA(x)
%
% made by Wonjun Hwang (haepaly) Oct. 2003

function [P,D] = PCA(x)    
    %[N, M]=size(x); % N: dim of data, M: num of data

	% P is eigenvector
	% D is eigen value
	
	%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	%% Write code here
    avg = 1;
    
    if (nargin == 1)
        opt = 0;
    end
    
    [N M] = size(x);
    
    avg = mean(x')';
    
    if (opt == 2)
        fprintf("# haepaly Lib: PCA");
    else
        x = x - repmat(avg, 1, M);
    end
    
    if (M < N)
        Cov = cov(x);
        [V D] = eig(Cov);
        
        [eigv, inx] = sort(diag(D));
        inx = inx([length(inx):-1:1]);
        D = eigv([length(inx):-1:1]);
        V = V(:, inx(1:length(inx)));
        
        P = x*V;
    else
        Cov = cov(x');
        [V D] = eig(Cov);
        
        [eigv, inx] = sort(diag(D));
        inx = inx([length(inx):-1:1]);
        D = eigv([length(inx):-1:1]);
        V = V(:, inx(1:length(inx)));
        
        P = V;
    end
    
    if (opt == 0)
        [N M] = size(P);
        for i = 1:M
            P(:, i) = P(:, i)./norm(P(:, i));
        end
        fprintf("# Orthonormality in PCA\n");
    end
end
