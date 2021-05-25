import request from '@/utils/request'

                
// 分页查询平台发送短信表列表
export function listPageMessage(query) {
  return request({
    url: '/admin/message/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询平台发送短信表列表
export function listMessage(query) {
  return request({
    url: '/admin/message/findList',
    method: 'post',
    data: query
  })
}

// 查询平台发送短信表详细
export function getMessage(id) {
  return request({
    url: '/admin/message/' + id,
    method: 'get'
  })
}

// 新增平台发送短信表
export function addMessage(data) {
  return request({
    url: '/admin/message',
    method: 'post',
    data: data
  })
}

// 修改平台发送短信表
export function updateMessage(data) {
  return request({
    url: '/admin/message',
    method: 'put',
    data: data
  })
}

// 删除平台发送短信表
export function delMessage(id) {
  return request({
    url: '/admin/message/' + id,
    method: 'delete'
  })
}


// 状态启用/停用平台发送短信表
export function changeStatus(data) {
  return request({
    url: '/admin/message/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出平台发送短信表
export function exportMessage(query) {
  return request({
    url: '/admin/message/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}