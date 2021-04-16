import request from '@/utils/request'


// 分页查询平台账务流水列表
export function listPageAccountFlow(query) {
  return request({
    url: '/admin/accountFlow/pageList',
    method: 'get',
    params: query
  })
}


// 分页查询平台账务流水列表
export function listAccountFlow(query) {
  return request({
    url: '/admin/accountFlow/findList',
    method: 'get',
    params: query
  })
}

// 查询平台账务流水详细
export function getAccountFlow(id) {
  return request({
    url: '/admin/accountFlow/' + id,
    method: 'get'
  })
}

// 新增平台账务流水
export function addAccountFlow(data) {
  return request({
    url: '/admin/accountFlow',
    method: 'post',
    data: data
  })
}

// 修改平台账务流水
export function updateAccountFlow(data) {
  return request({
    url: '/admin/accountFlow',
    method: 'put',
    data: data
  })
}

// 删除平台账务流水
export function delAccountFlow(id) {
  return request({
    url: '/admin/accountFlow/' + id,
    method: 'delete'
  })
}


// 状态启用/停用平台账务流水
export function changeStatus(data) {
  return request({
    url: '/admin/accountFlow/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出平台账务流水
export function exportAccountFlow(query) {
  return request({
    url: '/admin/accountFlow/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}