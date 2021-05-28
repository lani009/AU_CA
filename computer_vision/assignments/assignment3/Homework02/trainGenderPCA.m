clear all
close all

addpath('./Data/CelebA/');
addpath('./Src/');

load CelebA_mini.mat

[train_image, nImage, sy, sx ]= cell2vec(train_image);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% male
idx = find(train_attr==1);
train_image_male = train_image(:, idx);

[~, m] = size(train_image_male);

% centerization by mean
mu_male = mean(train_image_male, 2); % [n x 1]
figure, imshow(reshape(mu_male, [32 32]));
x = train_image_male - repmat(mu_male, [1 m]);

% training PCA
fprintf('Training PCA model...\n');
[U_male,S_male] = PCA(x); % U: eigenvectors
showImage(U_male);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% female
idx = find(train_attr==-1);
train_image_female = train_image(:, idx);

[n, m] = size(train_image_female);

% centerization by mean
mu_female = mean(train_image_female, 2); % [n x 1]
figure, imshow(reshape(mu_female, [32 32]));
x = train_image_female - repmat(mu_female, [1 m]);

% training PCA
fprintf('Training PCA model...\n');
[U_female,S_female] = PCA(x); % U: eigenvectors
showImage(U_female);

%%
% projecting the female to the male bisis
k=10;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Write code here
x = train_image_female;
mu = mean(train_image_male, 2);
U = U_male;

[~, m] = size(x);
y = U(:,1:k)'*(x - repmat(mu, [1 m]));
rx = U(:, 1:k)*y + repmat(mu, [1 m]);
showImage(x);
title('Female input');
showImage(rx);
title('Reconstructed image from male bais');

% projecting the female to the female bisis
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Write code here
mu = mean(train_image_female, 2);
U = U_female;

[~, m] = size(x);
y = U(:,1:k)'*(x - repmat(mu, [1 m]));
rx = U(:, 1:k)*y + repmat(mu, [1 m]);
showImage(rx);
title('Reconstructed image from female bais');


%%
% projecting the male to the female bisis
k=10;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Write code here
x = train_image_male;
mu = mean(train_image_female, 2);
U = U_female;

[~, m] = size(x);
y = U(:,1:k)'*(x - repmat(mu, [1 m]));
rx = U(:, 1:k)*y + repmat(mu, [1 m]);
showImage(x);
title('Male input');
showImage(rx);
title('Reconstructed image from female bais');

% projecting the male to the male bisis
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Write code here
mu = mean(train_image_male, 2);
U = U_male;

[~, m] = size(x);
y = U(:,1:k)'*(x - repmat(mu, [1 m]));
rx = U(:, 1:k)*y + repmat(mu, [1 m]);
showImage(rx);
title('Reconstructed image from male bais');
