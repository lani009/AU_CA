function [wout] = walshcode(n)
wout=[-1 -1;-1 1];

if n==1
    wout=[-1 -1; -1 1];
else
    for ii=1: n-1
    wout=[wout wout; wout -wout];
    end
end
    

    
    
