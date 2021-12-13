function [outregi] = shift(inregi, shiftr, shiftu)
% inregi: Vector or matrix
% shiftr: The amount of shift to the right
% shiftu: The amount of shift to the top -> discard
% outregi: Register output

shiftr = mod(shiftr, length(inregi));
outregi = inregi;
for i = 1 : shiftr
    last = outregi(length(inregi));
    for j = 1 : length(inregi)-1
        revJ = length(inregi) - j;
        outregi(revJ+1) = outregi(revJ);
    end
    outregi(1) = last;
end