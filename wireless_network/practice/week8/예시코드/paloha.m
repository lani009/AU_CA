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
    Spnum=Spnum+1;                              % 전송 된 총 패킷 수(Spnum) 하나 증가  
    Splen=Splen+Mplen(idx);                     % 전송 된 총 패킷 길이(Splen) 더하기 
    Wtime=Wtime+now_time-mgtime(idx);           % 패킷 전송 - 패킷 생성 시점 뺀 시간 총 합             
    Mstate(idx)=STANDBY;
    mgtime(idx)=now_time-Tint*log(1-rand);  % 다음 패킷 생성 시점
    mtime(idx)=mgtime(idx);                 % 다음 패킷 전송 시점
end

idx = find(mtime==now_time&Mstate==COLLISION);
if length(idx)>0
    Mstate(idx)=STANDBY;                    % collision 된 상태의 단말은 다시 standby 
    mtime(idx)=now_time-Rint*log(1-rand(1,length(idx))); % 재전송할 시점
end

idx=find(mtime == now_time);                % standby 상태
if length(idx)>0
    Mstate(idx)=TRANSMIT;                   % 전송시켜줌
    mtime(idx)=now_time+Mplen(idx)/Srate;   % 전송 끝난 시간 
    Tplen=Tplen+sum(Mplen(idx));            % 패킷 길이를 전송 패킷 길이에 합해줌
end

next_time=min(mtime);   % next state change time  