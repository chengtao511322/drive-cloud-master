import request from '@/utils/request'

                            
// 分页查询用户常用地址关联表列表
export function listPageUserCommonlyAddress(query) {
  return request({
    url: '/admin/userCommonlyAddress/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询用户常用地址关联表列表
export function listUserCommonlyAddress(query) {
  return request({
    url: '/admin/userCommonlyAddress/findList',
    method: 'post',
    data: query
  })
}

// 查询用户常用地址关联表详细
export function getUserCommonlyAddress(id) {
  return request({
    url: '/admin/userCommonlyAddress/' + id,
    method: 'get'
  })
}

// 新增用户常用地址关联表
export function addUserCommonlyAddress(data) {
  return request({
    url: '/admin/userCommonlyAddress',
    method: 'post',
    data: data
  })
}

// 修改用户常用地址关联表
export function updateUserCommonlyAddress(data) {
  return request({
    url: '/admin/userCommonlyAddress',
    method: 'put',
    data: data
  })
}

// 删除用户常用地址关联表
export function delUserCommonlyAddress(id) {
  return request({
    url: '/admin/userCommonlyAddress/' + id,
    method: 'delete'
  })
}


// 状态启用/停用用户常用地址关联表
export function changeStatus(data) {
  return request({
    url: '/admin/userCommonlyAddress/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出用户常用地址关联表
export function exportUserCommonlyAddress(query) {
  return request({
    url: '/admin/userCommonlyAddress/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}