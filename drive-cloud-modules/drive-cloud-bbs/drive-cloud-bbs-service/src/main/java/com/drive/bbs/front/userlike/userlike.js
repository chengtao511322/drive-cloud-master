import request from '@/utils/request'

                  
// 分页查询用户点赞表列表
export function listPageUserLike(query) {
  return request({
    url: '/bbs/userLike/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询用户点赞表列表
export function listUserLike(query) {
  return request({
    url: '/bbs/userLike/findList',
    method: 'post',
    data: query
  })
}

// 查询用户点赞表详细
export function getUserLike(id) {
  return request({
    url: '/bbs/userLike/' + id,
    method: 'get'
  })
}

// 新增用户点赞表
export function addUserLike(data) {
  return request({
    url: '/bbs/userLike',
    method: 'post',
    data: data
  })
}

// 修改用户点赞表
export function updateUserLike(data) {
  return request({
    url: '/bbs/userLike',
    method: 'put',
    data: data
  })
}

// 删除用户点赞表
export function delUserLike(id) {
  return request({
    url: '/bbs/userLike/' + id,
    method: 'delete'
  })
}


// 状态启用/停用用户点赞表
export function changeStatus(data) {
  return request({
    url: '/bbs/userLike/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出用户点赞表
export function exportUserLike(query) {
  return request({
    url: '/bbs/userLike/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}