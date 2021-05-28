% Train script for EigenFace
% made by Wonjun Hwang (wjhwang@ajou.ac.kr) for Computer Vision lecture
% date: 2021-05-14

clear all
close all

addpath('./Data/CelebA/');
addpath('./Src/');

load CelebA_mini.mat

[train_image, nImage, sy, sx ]= cell2vec(train_image);

% Show the face images
showImage(train_image);
title('Input Image');
[n, m] = size(train_image);

% centerization by global mean
mu = mean(train_image, 2); % [n x 1]
x = train_image - repmat(mu, [1 m]);

% training PCA
fprintf('Training PCA model...\n');
[U,S] = PCA(x); % U: eigenvectors
showImage(U);
title('EigenFace');

% save PCA model
save ./Model/PCAModel.mat U S mu


%% Selecting the first 50 basis eigenvectors
k = 50; % max is 1024

% linear projection using PCA 
y = U(:,1:k)'*(train_image - repmat(mu, [1 m]));

% reconstructed image
rx = U(:, 1:k)*y + repmat(mu, [1 m]);
showImage(rx);

% calculate the difference between the reconstructed images and the input images
diff = (rx - train_image).^2;
diff = sqrt(mean(diff(:)));
fprintf('[1] Average Pixel Difference (k=%d) is %0.6f\n', k, diff);

%% Selecting the first 1000 basis eigenvectors
k = 1000; % max is 1024

% linear projection using PCA 
y = U(:,1:k)'*(train_image - repmat(mu, [1 m]));

% reconstructed image
rx = U(:, 1:k)*y + repmat(mu, [1 m]);
showImage(rx);

diff = (rx - train_image).^2;
diff = sqrt(mean(diff(:)));
fprintf('[2] Average Pixel Difference (k=%d) is %0.6f\n', k, diff);

