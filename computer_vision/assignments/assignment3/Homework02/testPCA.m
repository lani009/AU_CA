% Train script for EigenFace
% made by Wonjun Hwang (wjhwang@ajou.ac.kr) for Computer Vision lecture
% date: 2021-05-14

clear all
close all

addpath('./Data/CelebA/');
addpath('./Src/');

load CelebA_mini.mat

[test_image, nImage, sy, sx ]= cell2vec(test_image);

if (exist('./Model/PCAModel.mat')~= 0 )
    load ./Model/PCAModel.mat
end

% Show the test face images
showImage(test_image);
[n, m] = size(test_image);

% Centeralized by training mu, not test mu
x = test_image - repmat(mu, [1 m]);

%% Selecting the first 50 basis eigenvectors
k = 50; % max is 1024

% linear projection using PCA 
y = U(:,1:k)'*(test_image - repmat(mu, [1 m]));

% reconstructed image
rx = U(:, 1:k)*y + repmat(mu, [1 m]);
showImage(rx);

% calculate the difference between the reconstructed images and the input images
diff = (rx - test_image).^2;
diff = sqrt(mean(diff(:)));
fprintf('[1] Average Pixel Difference (k=%d) is %0.6f\n', k, diff);

%% Selecting the first 1000 basis eigenvectors
k = 1000; % max is 1024

% linear projection using PCA 
y = U(:,1:k)'*(test_image - repmat(mu, [1 m]));

% reconstructed image
rx = U(:, 1:k)*y + repmat(mu, [1 m]);
showImage(rx);

diff = (rx - test_image).^2;
diff = sqrt(mean(diff(:)));
fprintf('[2] Average Pixel Difference (k=%d) is %0.6f\n', k, diff);

