import request from '@/utils/request'

                              
// 分页查询论坛帖子回复表列表
export function listPageInvitationReply(query) {
  return request({
    url: '/bbs/invitationReply/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询论坛帖子回复表列表
export function listInvitationReply(query) {
  return request({
    url: '/bbs/invitationReply/findList',
    method: 'post',
    data: query
  })
}

// 查询论坛帖子回复表详细
export function getInvitationReply(id) {
  return request({
    url: '/bbs/invitationReply/' + id,
    method: 'get'
  })
}

// 新增论坛帖子回复表
export function addInvitationReply(data) {
  return request({
    url: '/bbs/invitationReply',
    method: 'post',
    data: data
  })
}

// 修改论坛帖子回复表
export function updateInvitationReply(data) {
  return request({
    url: '/bbs/invitationReply',
    method: 'put',
    data: data
  })
}

// 删除论坛帖子回复表
export function delInvitationReply(id) {
  return request({
    url: '/bbs/invitationReply/' + id,
    method: 'delete'
  })
}


// 状态启用/停用论坛帖子回复表
export function changeStatus(data) {
  return request({
    url: '/bbs/invitationReply/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出论坛帖子回复表
export function exportInvitationReply(query) {
  return request({
    url: '/bbs/invitationReply/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}