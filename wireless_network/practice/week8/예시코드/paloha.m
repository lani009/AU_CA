function [next_time]=paloha(now_time)

global STANDBY TRANSMIT COLLISION      % definition of the global variable
global Srate Plen
global Mnum Mplen Mstate
global Tint Rint
global Spnum Splen Tplen Wtime

persistent mgtime mtime          % definition of the static variable

if now_time < 0                              % initialization of access terminals
    rand('state',sum(100*clock));           % resetting of the random table
    mgtime= -Tint * log(1-rand(1,Mnum));    % packet generation time 
    mtime=mgtime;                           % packet transmitting time                 
    Mstate=zeros(1,Mnum);                   % state of terminals
    Mplen(1:Mnum)=Plen;                     % packet length   
    next_time=min(mtime);                   
    return
end

idx = find(mtime==now_time & Mstate==TRANSMIT); % finding of the terminal which transmission succeeded
if length(idx)>0    
    Spnum=Spnum+1;                              % ���� �� �� ��Ŷ ��(Spnum) �ϳ� ����  
    Splen=Splen+Mplen(idx);                     % ���� �� �� ��Ŷ ����(Splen) ���ϱ� 
    Wtime=Wtime+now_time-mgtime(idx);           % ��Ŷ ���� - ��Ŷ ���� ���� �� �ð� �� ��             
    Mstate(idx)=STANDBY;
    mgtime(idx)=now_time-Tint*log(1-rand);  % ���� ��Ŷ ���� ����
    mtime(idx)=mgtime(idx);                 % ���� ��Ŷ ���� ����
end

idx = find(mtime==now_time&Mstate==COLLISION);
if length(idx)>0
    Mstate(idx)=STANDBY;                    % collision �� ������ �ܸ��� �ٽ� standby 
    mtime(idx)=now_time-Rint*log(1-rand(1,length(idx))); % �������� ����
end

idx=find(mtime == now_time);                % standby ����
if length(idx)>0
    Mstate(idx)=TRANSMIT;                   % ���۽�����
    mtime(idx)=now_time+Mplen(idx)/Srate;   % ���� ���� �ð� 
    Tplen=Tplen+sum(Mplen(idx));            % ��Ŷ ���̸� ���� ��Ŷ ���̿� ������
end

next_time=min(mtime);   % next state change time  