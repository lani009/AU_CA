function [outImage, nImage, sy, sx] = cell2vec(inImage)
    nImage = length(inImage);
    [sy, sx] = size(inImage{1});

    % make column-wised vectors from images
    image = zeros(sx*sy, nImage);
    for i=1:nImage
        temp = im2double(inImage{i});
        image(:, i) = temp(:);
    end

    % replace image -> train_image
    outImage = image;
end