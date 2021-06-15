import request from '@/utils/request'

                                            
// 分页查询论坛帖子表列表
export function listPageInvitation(query) {
  return request({
    url: '/bbs/invitation/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询论坛帖子表列表
export function listInvitation(query) {
  return request({
    url: '/bbs/invitation/findList',
    method: 'post',
    data: query
  })
}

// 查询论坛帖子表详细
export function getInvitation(id) {
  return request({
    url: '/bbs/invitation/' + id,
    method: 'get'
  })
}

// 新增论坛帖子表
export function addInvitation(data) {
  return request({
    url: '/bbs/invitation',
    method: 'post',
    data: data
  })
}

// 修改论坛帖子表
export function updateInvitation(data) {
  return request({
    url: '/bbs/invitation',
    method: 'put',
    data: data
  })
}

// 删除论坛帖子表
export function delInvitation(id) {
  return request({
    url: '/bbs/invitation/' + id,
    method: 'delete'
  })
}


// 状态启用/停用论坛帖子表
export function changeStatus(data) {
  return request({
    url: '/bbs/invitation/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出论坛帖子表
export function exportInvitation(query) {
  return request({
    url: '/bbs/invitation/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}