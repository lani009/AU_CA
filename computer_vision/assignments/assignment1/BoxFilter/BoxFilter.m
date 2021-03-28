% Function {out parameter}={function name}(in parameter}
%
% input parameter definition
% im = input image
% sizeofkernel = Size of Kernel
%
% output parameter definition
% output = output image
%
function [output]=BoxFilter(im, sizeofkernel)

    % kernel generation part
    kernel = ones(sizeofkernel);
    [ksizey,ksizex] = size(kernel);
    kernel = kernel / (ksizey*ksizex);
    fprintf('sum of kernel coefficient is %d\n', sum(sum(kernel)));
    % center is the anchor point of the kernel 
    % e.g., 5x5 -> center = 3
    %       3x3 -> center = 2
	%
	% fill the following "center" code
	% center = ?????
	% write your own code here
    center = (ksizex+1) / 2;
    
    fprintf('center of kernel is %d\n', center);
    
    [height, width]=size(im);
    
    output = zeros(height, width);
    % F(x,y)
	% fill the following four for routine
	% write your own code here
    for i = (center):(width - center + 1)
        for j = (center):(height - center + 1)
            filter_sum = 0;
            for u = (-center + 1):(center - 1)
                for v = (-center + 1):(center - 1)
                    filter_sum = filter_sum + im(i+u, j+v) * kernel(center - u, center - v);
                end
            end
            output(i, j) = filter_sum;
        end
    end
end