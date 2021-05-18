function [x]=exporv(lambda, N)
    x = zeros(0, N);
    
    for i=1:2
        x(i) = -(1/lambda)*log(1-rand(1));
    end
end