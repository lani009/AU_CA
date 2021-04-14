function [out]=CrossCorrelation(H, F, y, x)
    [height_kernel, width_kernel]=size(H);    
    out=0;
    for i=1:height_kernel
        for j=1:width_kernel
            out = out + H(i,j)*F(y+i,x+j);            
        end
    end    
end