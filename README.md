# MeetingServer
视频会议的后端
主要使用websocket管理视频会议的房间。
导入`meeting.sql`到数据库，修改`application.properties`中的连接配置
主要逻辑代码在
`cn.nicenan.meeting.websocket.WebrtcWS`
`cn.nicenan.meeting.service.impl.WebrtcRoomServiceImpl`

删除https方法
这次commit提交的全部删掉。
https://github.com/nnn149/MeetingServer/commit/a3dc96ccc3e0f26c47c9d461070d9bdd1aae3626

前端
https://github.com/nnn149/MeetingWeb
