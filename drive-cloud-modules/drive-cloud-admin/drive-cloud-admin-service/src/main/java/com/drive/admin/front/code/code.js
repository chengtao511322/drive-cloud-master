import request from '@/utils/request'

                          
// 分页查询字典表列表
export function listPageCode(query) {
  return request({
    url: '/admin/code/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询字典表列表
export function listCode(query) {
  return request({
    url: '/admin/code/findList',
    method: 'post',
    data: query
  })
}

// 查询字典表详细
export function getCode(codeId) {
  return request({
    url: '/admin/code/' + codeId,
    method: 'get'
  })
}

// 新增字典表
export function addCode(data) {
  return request({
    url: '/admin/code',
    method: 'post',
    data: data
  })
}

// 修改字典表
export function updateCode(data) {
  return request({
    url: '/admin/code',
    method: 'put',
    data: data
  })
}

// 删除字典表
export function delCode(codeId) {
  return request({
    url: '/admin/code/' + codeId,
    method: 'delete'
  })
}


// 状态启用/停用字典表
export function changeStatus(data) {
  return request({
    url: '/admin/code/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出字典表
export function exportCode(query) {
  return request({
    url: '/admin/code/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}