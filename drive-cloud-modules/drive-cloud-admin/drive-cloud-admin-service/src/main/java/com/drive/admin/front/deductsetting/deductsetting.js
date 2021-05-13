import request from '@/utils/request'

                  
// 分页查询提成设置表列表
export function listPageDeductSetting(query) {
  return request({
    url: '/admin/deductSetting/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询提成设置表列表
export function listDeductSetting(query) {
  return request({
    url: '/admin/deductSetting/findList',
    method: 'post',
    data: query
  })
}

// 查询提成设置表详细
export function getDeductSetting(${pkColumn.propertyName}) {
  return request({
    url: '/admin/deductSetting/' + ${pkColumn.propertyName},
    method: 'get'
  })
}

// 新增提成设置表
export function addDeductSetting(data) {
  return request({
    url: '/admin/deductSetting',
    method: 'post',
    data: data
  })
}

// 修改提成设置表
export function updateDeductSetting(data) {
  return request({
    url: '/admin/deductSetting',
    method: 'put',
    data: data
  })
}

// 删除提成设置表
export function delDeductSetting(${pkColumn.propertyName}) {
  return request({
    url: '/admin/deductSetting/' + ${pkColumn.propertyName},
    method: 'delete'
  })
}


// 状态启用/停用提成设置表
export function changeStatus(data) {
  return request({
    url: '/admin/deductSetting/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出提成设置表
export function exportDeductSetting(query) {
  return request({
    url: '/admin/deductSetting/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}