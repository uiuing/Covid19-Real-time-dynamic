#!/bin/sh
# # # #
#  
#  @param China-COVID-19进程监听
# 
#  @param ©️uiuing.top   2020
#
# # # #
source /etc/profile
now=`date +"%Y-%m-%d %H:%M:%S"`
file_name="/********/pushdemo.log"  
pid=0
proc_num() 
{
    num=`ps -ef | grep 'java -jar Data_monitoring.jar' | grep -v grep | wc -l` 
    return $num 
}
proc_id()
{  
    pid=`ps -ef | grep 'java -jarData_monitoring.jar' | grep -v grep | awk '{print $2}'`  
} 
proc_num  
number=$? 
if [ $number -eq 0 ]  
then
    cd /*******/
    (
        ./runcovid.sh
    )
    nohup java -jar Data_monitoring.jar  &  
    proc_id 
    echo "${now} 进程数 = ${number} 重启应用服务：push -> pid = ${pid}" >> $file_name  
fi